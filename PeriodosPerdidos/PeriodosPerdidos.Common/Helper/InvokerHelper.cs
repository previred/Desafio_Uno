using Polly;
using System;
using System.Diagnostics;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.Extensions.Logging;

namespace PeriodosPerdidos.Common.Helper
{
    public static class InvokerHelper
    {
        /// <summary>
        /// Solo invocar sin registrar trace con el resultado de la dependencia. 
        /// La responsabilidad de regitrar el trace lo asume quien ejecuta "Invoque"
        /// </summary>
        /// <param name="httpClient"></param>
        /// <param name="httpRequestMessage"></param>
        /// <param name="baseServiceContext">Una instancia para establecer en el contexto de Polly (pollyContext["BaseServiceContext"])</param>
        /// <param name="finishedAction">Un funcion o expresion que recibe (startTime, clock.Elapsed, httpResponseMessage.IsSuccessStatusCode, httpResponseMessage.StatusCode) </param>
        /// <returns></returns>
        public static async Task<HttpResponseMessage> Invoke(HttpClient httpClient, HttpRequestMessage httpRequestMessage, BaseServiceContext baseServiceContext, Action<DateTime, TimeSpan, bool, HttpStatusCode> finishedAction)
        {
            Context pollyContext = new Context();
            pollyContext["BaseServiceContext"] = baseServiceContext;

            httpRequestMessage.SetPolicyExecutionContext(pollyContext);

            Stopwatch clock = Stopwatch.StartNew();
            DateTime startTime = DateTime.UtcNow;

            HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);

            finishedAction(startTime, clock.Elapsed, httpResponseMessage.IsSuccessStatusCode, httpResponseMessage.StatusCode);

            return httpResponseMessage;
        }

        public static async Task<HttpResponseMessage> InvokeAndTraceDependency(HttpClient httpClient, HttpRequestMessage httpRequestMessage, BaseServiceContext baseServiceContext)
        {
            Context pollyContext = new Context();
            pollyContext["BaseServiceContext"] = baseServiceContext;

            httpRequestMessage.SetPolicyExecutionContext(pollyContext);

            Stopwatch clock = Stopwatch.StartNew();
            DateTime startTime = DateTime.UtcNow;

            HttpResponseMessage httpResponseMessage = await httpClient.SendAsync(httpRequestMessage);

            baseServiceContext.Logger.LogInformation($"Dependency --> {baseServiceContext.ActivityId} {baseServiceContext.Dependency.TypeName} {baseServiceContext.Dependency.Name} {baseServiceContext.Dependency.Target} {httpResponseMessage.StatusCode} {startTime} {clock.Elapsed}");

            return httpResponseMessage;
        }
    }
}
