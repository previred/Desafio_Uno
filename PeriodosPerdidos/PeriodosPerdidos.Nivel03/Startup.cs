using MediatR;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Diagnostics;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.OpenApi.Models;
using PeriodosPerdidos.Common.Helper;
using System.Diagnostics;
using System.Text;
using System.Text.Json.Serialization;

namespace PeriodosPerdidos.Nivel03
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {
            //se configura mediator
            services.AddMediatR(System.Reflection.Assembly.GetExecutingAssembly());

            services.AddScoped<IRequestHandler<Business.Commands.Nivel03.Gdd, Business.Commands.Nivel03.GddResponse>, Business.Processors.Nivel03.Gdd>();

            services.AddNamedHttpClients(Configuration,
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

            services.AddControllers()
                    .AddJsonOptions(options =>
                    {
                        options.JsonSerializerOptions.Converters.Add(new JsonStringEnumConverter());
                    });

            services.AddSwaggerGen(c =>
            {
                c.SwaggerDoc("v1", new OpenApiInfo { Title = "PeriodosPerdidos.Nivel03", Version = "v1" });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            //aqui se configurar los pipeline cuando se ejecuta la aplicacion, llamado middleware

            //lo primero se le signan un id unico al request
            app.Use(async (context, next) =>
            {
                Activity activity = new Activity("Periodos.Perdidos");
                activity.SetIdFormat(ActivityIdFormat.W3C);
                activity.Start();

                //se puede agregar muchos mas datos, hacer fluir datos, etc //X-Forwarded-For

                await next();

                activity.Stop();
            });

            //se capturan todas las expcepciones que ocurran, aqui se escribe log (logger), hay muchas opciones como guardar el log
            app.UseExceptionHandler(errorApp =>
            {
                errorApp.Run(async context =>
                {
                    IExceptionHandlerPathFeature exceptionHandlerPathFeature = context.Features.Get<IExceptionHandlerPathFeature>();

                    context.Response.StatusCode = 500;
                    context.Response.ContentType = "application/json;charset=" + Encoding.UTF8.WebName;

                    string error = exceptionHandlerPathFeature?.Error.Message;
                    if (error == null) error = string.Empty;

                    await context.Response.WriteAsync("{\"code\": -1, \"reason\" : \"Internal Error " + error + " \"}");
                });
            });

            //si estas en modo desarrollo levanta una pagina mas amigable para los errores
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            //se configura el swagger, esto solo se levanta en modo desarrollo, lo deje publico para instalarlo en azure
            app.UseSwagger();
            app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "Periodos Perdidos Nivel 03 - v1"));

            //te obliga a usar https
            app.UseHttpsRedirection();

            //se configura el router de los controller
            app.UseRouting();

            //por ultimo lebanta los controller para entregar la informacion
            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}