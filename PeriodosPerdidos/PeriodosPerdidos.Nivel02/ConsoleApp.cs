using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.IO;
using System.Net.Http;
using System.Reflection;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Nivel02
{
    public class ConsoleApp : IHostedService
    {
        private readonly ILogger<ConsoleApp> _logger;
        private readonly HttpClient _httpClient;

        public ConsoleApp(ILogger<ConsoleApp> logger, IHttpClientFactory clientFactory)
        {
            _logger = logger;
            _httpClient = clientFactory.CreateClient("GDD");
        }

        public async Task StartAsync(CancellationToken cancellationToken)
        {
            string path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "Files", "Request.json");
            string jsonBody = File.ReadAllText(path);

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

            Models.Request request = Newtonsoft.Json.JsonConvert.DeserializeObject<Models.Request>(jsonBody);
            Models.ResultPeriods result = Newtonsoft.Json.JsonConvert.DeserializeObject<Models.ResultPeriods>(BodyResultString);

            string pathResult = Path.Combine("C:\\Temp", $"Result_Nivel02_{DateTime.Now.ToShortDateString()}.txt");
            StreamWriter StreamWriter = new(pathResult);
            using (StreamWriter)
            {
                StreamWriter.WriteLine($"fecha creación: {request.FechaCreacion}");
                StreamWriter.WriteLine($"fecha fin: {request.FechaFin}");
                StreamWriter.WriteLine($"fechas recibidas: { string.Join(", ", request.Fechas)}");
                StreamWriter.WriteLine($"fechas faltantes: { string.Join(", ", result.FechasFaltantes)}");
            }

            _logger.LogInformation($"{"".PadLeft(100, '-')}");
            _logger.LogInformation($"Archivo de salida -------------------------------> {pathResult}");
            _logger.LogInformation($"{"".PadLeft(100, '-')}");
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
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
