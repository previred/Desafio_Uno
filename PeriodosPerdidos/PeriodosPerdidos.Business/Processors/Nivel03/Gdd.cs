using Microsoft.Extensions.Logging;
using PeriodosPerdidos.Business.Commands.Nivel03;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Business.Processors.Nivel03
{
    public class Gdd : MediatR.IRequestHandler<Commands.Nivel03.Gdd, Commands.Nivel03.GddResponse>
    {
        private readonly ILogger<Gdd> _logger;
        private readonly HttpClient _httpClient;
        public Gdd(ILogger<Gdd> logger, IHttpClientFactory clientFactory)
        {
            _logger = logger;
            _httpClient = clientFactory.CreateClient("GDD");
        }

        public async Task<GddResponse> Handle(Commands.Nivel03.Gdd request, CancellationToken cancellationToken)
        {
            GddResponse result = new();

            //validamos los datos de entrada
            FluentValidation.Results.ValidationResult validationResult = (new Commands.Nivel03.GddValidator()).Validate(request);
            if (!validationResult.IsValid)
            {
                result.Success = false;
                result.ReasonPhrase = string.Join(", ", validationResult.Errors);
                return result;
            }

            _ = DateTime.TryParse(request.FechaCreacion, out DateTime fechaCreacion);
            _ = DateTime.TryParse(request.FechaFin, out DateTime fechaFin);
            List<DateTime> fechas = new List<DateTime>();
            foreach (string fecha in request.Fechas)
            {
                _ = DateTime.TryParse(fecha, out DateTime resultParse);
                fechas.Add(resultParse);
            }

            //esta validacion la debi haber hecho en fluentvalidation, no alcance por tiempo
            if (fechaCreacion > fechaFin)
            {
                result.Success = false;
                result.ReasonPhrase = "La fecha creacion no puede ser mayor a la fecha fin";
                return result;
            }

            string jsonBody = Newtonsoft.Json.JsonConvert.SerializeObject(request);

            StringContent content = new StringContent(jsonBody, Encoding.UTF8, "application/json");
            HttpRequestMessage httpRequestMessage = new HttpRequestMessage(HttpMethod.Post, $"{_httpClient.BaseAddress}/ServicioFechas/Evaluar")
            {
                Content = content
            };

            HttpResponseMessage httpResponseMessage = await Send(httpRequestMessage);

            if (!httpResponseMessage.IsSuccessStatusCode)
            {
                throw new HttpRequestException(httpResponseMessage.ReasonPhrase, new Exception(), httpResponseMessage.StatusCode);
            }

            string BodyResultString = await httpResponseMessage.Content.ReadAsStringAsync();

            result = Newtonsoft.Json.JsonConvert.DeserializeObject<GddResponse>(BodyResultString);
            result.Fechas = fechas.ToArray();

            result.Success = true;

            return result;
        }

        private async Task<HttpResponseMessage> Send(HttpRequestMessage request)
        {
            HttpResponseMessage httpResponseMessage = await Common.Helper.InvokerHelper.InvokeAndTraceDependency(
                _httpClient,
                request,
                new Common.Helper.BaseServiceContext(_logger,
                    Guid.Empty,
                    Guid.NewGuid().ToString(),
                    request.RequestUri.AbsoluteUri,
                    new Common.Helper.BaseServiceContextDependency { TypeName = "http", Name = "GDD", Target = "Post" }));
            return httpResponseMessage;
        }
    }
}
