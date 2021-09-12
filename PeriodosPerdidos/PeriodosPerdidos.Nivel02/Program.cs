using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using PeriodosPerdidos.Common.Helper;

namespace PeriodosPerdidos.Nivel02
{
    static class Program
    {
        static void Main(string[] args)
        {
            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args) =>
            Host.CreateDefaultBuilder(args)
                .ConfigureAppConfiguration((hostingContext, config) =>
                {

                })
                .ConfigureServices((hostContext, services) =>
                {
                    services.AddNamedHttpClients(hostContext.Configuration,
                        onTransientError: (transientHttpErrorInfo, pollyContext) => {
                            BaseServiceContext baseServiceContext = pollyContext["BaseServiceContext"] as BaseServiceContext;
                            baseServiceContext.Logger.LogInformation($"onTransientError --> {baseServiceContext.ActivityId} {baseServiceContext.Dependency.TypeName} {baseServiceContext.Dependency.Name} {baseServiceContext.Dependency.Target} {transientHttpErrorInfo.TimeSpan} {transientHttpErrorInfo.StatusCode} {transientHttpErrorInfo.EndPointName}");
                        },
                        onCircuitBreakeOpened: (circuitBreakerInfo, pollyContext) => {
                            BaseServiceContext baseServiceContext = pollyContext["BaseServiceContext"] as BaseServiceContext;
                            baseServiceContext.Logger.LogInformation($"onCircuitBreakeOpened --> {baseServiceContext.ActivityId} {baseServiceContext.Dependency.TypeName} {baseServiceContext.Dependency.Name} {baseServiceContext.Dependency.Target} {circuitBreakerInfo.TimeSpan} {circuitBreakerInfo.EndPointName}");
                        },
                        onCircuitBreakeClosed: (endPointName, pollyContext) => {
                            BaseServiceContext baseServiceContext = pollyContext["BaseServiceContext"] as BaseServiceContext;
                            baseServiceContext.Logger.LogInformation($"onCircuitBreakeClosed --> {baseServiceContext.ActivityId} {baseServiceContext.Dependency.TypeName} {baseServiceContext.Dependency.Name} {baseServiceContext.Dependency.Target} {endPointName}");
                        }
                    );

                    services.AddSingleton<IHostedService, ConsoleApp>();

                })
                .ConfigureLogging((hostingContext, logging) =>
                {
                    logging.Services.AddLogging();
                });
    }
}
