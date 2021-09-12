using MediatR;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using System;

namespace PeriodosPerdidos.Nivel01
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
                    services.AddSingleton<Abstractions.Repositories.IDateService, Repository.Dates.DateService>();

                    //se configura mediator
                    services.AddMediatR(System.Reflection.Assembly.GetExecutingAssembly());

                    //se configura los comandos y el procesador que resuelve el comando de mediator
                    services.AddScoped<IRequestHandler<Business.Commands.Date.Evaluate, Business.Commands.Date.EvaluateResponse>, Business.Processors.Date.Evaluate>();

                    services.AddSingleton<IHostedService, ConsoleApp>();
                })
                .ConfigureLogging((hostingContext, logging) =>
                {
                    logging.Services.AddLogging();
                });
    }
}