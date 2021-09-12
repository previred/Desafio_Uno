using MediatR;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Threading;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Nivel01
{
    public class ConsoleApp : IHostedService
    {
        private readonly IMediator _mediator;
        private readonly ILogger<ConsoleApp> _logger;

        public ConsoleApp(IMediator mediator, ILogger<ConsoleApp> logger)
        {
            _mediator = mediator;
            _logger = logger;
        }

        public async Task StartAsync(CancellationToken cancellationToken)
        {
            string path = Path.Combine(Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location), "Files", "Request.json");
            string readText = File.ReadAllText(path);

            Business.Commands.Date.Evaluate request = Newtonsoft.Json.JsonConvert.DeserializeObject<Business.Commands.Date.Evaluate>(readText);
            Business.Commands.Date.EvaluateResponse response = await _mediator.Send(request);

            string pathResult = Path.Combine("C:\\Temp", $"Result_Nivel01_{DateTime.Now.ToShortDateString()}.json");
            Common.Models.ResultPeriods result = new()
            {
                Id = response.Id,
                FechaCreacion = response.FechaCreacion.ToString("yyyy-MM-dd"),
                FechaFin = response.FechaFin.ToString("yyyy-MM-dd"),
                FechasFaltantes = response.FechasFaltantes.Select(ff => ff.ToString("yyyy-MM-dd")).ToArray()
            };

            StreamWriter StreamWriter = new(pathResult);
            using (StreamWriter)
            {
                StreamWriter.Write(Newtonsoft.Json.JsonConvert.SerializeObject(result, Newtonsoft.Json.Formatting.Indented));
            }

            _logger.LogInformation($"{"".PadLeft(100, '-')}");
            _logger.LogInformation($"Archivo de salida -------------------------------> {pathResult}");
            _logger.LogInformation($"{"".PadLeft(100, '-')}");
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            return Task.CompletedTask;
        }
    }
}