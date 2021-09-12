using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Polly;
using System;
using System.Collections.Generic;

namespace PeriodosPerdidos.Common.Helper
{
    public static class NamedHttpClientsExtensions
    {
        public static IServiceCollection AddNamedHttpClients(this IServiceCollection services, IConfiguration configuration, Action<TransientHttpErrorInfo, Polly.Context> onTransientError, Action<CircuitBreakerInfo, Polly.Context> onCircuitBreakeOpened, Action<string, Polly.Context> onCircuitBreakeClosed)
        {
            RestApisUrl RestApisUrl = configuration.GetSection("RestApisUrls").Get<RestApisUrl>();

            ValidateNamedHttpClients(services, RestApisUrl);

            CreateNamedHttpClients(services, RestApisUrl, onTransientError, onCircuitBreakeOpened, onCircuitBreakeClosed);

            return services;
        }

        static void ValidateNamedHttpClients(IServiceCollection services, RestApisUrl restApisUrl)
        {
            if (services == null)
                throw new ArgumentNullException(nameof(services));

            restApisUrl.Collection.ForEach(url =>
            {
                if (string.IsNullOrWhiteSpace(url.Name) || string.IsNullOrWhiteSpace(url.Endpoint) || string.IsNullOrWhiteSpace(url.Accept))
                    throw new HttpClientsConfigurationException("NamedHttpClients - Name, Endpoint or Accept is empty");
            });
        }

        static void CreateNamedHttpClients(IServiceCollection services, RestApisUrl restApisUrl, Action<TransientHttpErrorInfo, Polly.Context> onTransientError, Action<CircuitBreakerInfo, Polly.Context> onCircuitBreakeOpened, Action<string, Polly.Context> onCircuitBreakeClosed)
        {
            restApisUrl.Collection.ForEach(urlConfiguration =>
            {
                IHttpClientBuilder httpClientBuilder = services.AddHttpClient(urlConfiguration.Name, configureClient =>
                {
                    configureClient.BaseAddress = new Uri(urlConfiguration.Endpoint);
                    configureClient.DefaultRequestHeaders.Add("Accept", urlConfiguration.Accept);

                    if (!string.IsNullOrWhiteSpace(urlConfiguration.Token))
                    {
                        configureClient.DefaultRequestHeaders.Add("Authorization", $"Bearer {urlConfiguration.Token}");
                    }

                    foreach (Header header in urlConfiguration.Headers)
                    {
                        configureClient.DefaultRequestHeaders.Add(header.Name, header.Value);
                    }
                });

                AddTimeoutPolicy(httpClientBuilder, urlConfiguration);
                AddRetryPolicy(httpClientBuilder, urlConfiguration, onTransientError);
                AddCircuitBreakerPolicy(httpClientBuilder, urlConfiguration, onCircuitBreakeOpened, onCircuitBreakeClosed);
            });
        }

        static void AddTimeoutPolicy(IHttpClientBuilder httpClientBuilder, Url urlConfiguration)
        {
            if (urlConfiguration.TimeoutInSeconds > 0)
                httpClientBuilder.AddPolicyHandler(Policy.TimeoutAsync(TimeSpan.FromSeconds(urlConfiguration.TimeoutInSeconds)).AsAsyncPolicy<System.Net.Http.HttpResponseMessage>());
        }

        static void AddRetryPolicy(IHttpClientBuilder httpClientBuilder, Url urlConfiguration, Action<TransientHttpErrorInfo, Polly.Context> onTransientError)
        {
            if (urlConfiguration.TransientHttpErrors.WaitAndRetryClassic.Enable)
            {
                httpClientBuilder.AddTransientHttpErrorPolicy(policy =>
                    policy.WaitAndRetryAsync(
                        urlConfiguration.TransientHttpErrors.WaitAndRetryClassic.TotalRetry,
                        _ => TimeSpan.FromMilliseconds(urlConfiguration.TransientHttpErrors.WaitAndRetryClassic.WaitMilliseconds),
                        onRetry: (httpResponseMessage, timeSpan, retryCount, context) =>
                        {
                            TransientHttpErrorInfo transientHttpErrorInfo = new TransientHttpErrorInfo
                            {
                                EndPointName = urlConfiguration.Name,
                                StatusCode = ((int)httpResponseMessage.Result.StatusCode).ToString(),
                                ReasonPhrase = httpResponseMessage.Result.ReasonPhrase,
                                TimeSpan = timeSpan,
                                RetryCount = retryCount
                            };

                            onTransientError(transientHttpErrorInfo, context);
                        }
                    )
                );
            }

            if (urlConfiguration.TransientHttpErrors.WaitAndRetryExponential.Enable && !urlConfiguration.TransientHttpErrors.WaitAndRetryClassic.Enable)
            {
                List<TimeSpan> timeSpans = new List<TimeSpan>();

                for (short retry = 1; retry <= urlConfiguration.TransientHttpErrors.WaitAndRetryExponential.TotalRetry; retry++)
                    timeSpans.Add(TimeSpan.FromMilliseconds(retry * urlConfiguration.TransientHttpErrors.WaitAndRetryExponential.WaitMilliseconds));

                httpClientBuilder.AddTransientHttpErrorPolicy(policy =>
                    policy.WaitAndRetryAsync(timeSpans,
                        onRetry: (httpResponseMessage, timeSpan, retryCount, context) =>
                        {
                            TransientHttpErrorInfo transientHttpErrorInfo = new TransientHttpErrorInfo
                            {
                                EndPointName = urlConfiguration.Name,
                                StatusCode = ((int)httpResponseMessage.Result.StatusCode).ToString(),
                                ReasonPhrase = httpResponseMessage.Result.ReasonPhrase,
                                TimeSpan = timeSpan,
                                RetryCount = retryCount
                            };

                            onTransientError(transientHttpErrorInfo, context);
                        }
                    )
                );
            }
        }

        static void AddCircuitBreakerPolicy(IHttpClientBuilder httpClientBuilder, Url urlConfiguration, Action<CircuitBreakerInfo, Polly.Context> onCircuitBreakeOpened, Action<string, Polly.Context> onCircuitBreakeClosed)
        {
            if (urlConfiguration.TransientHttpErrors.CircuitBreaker.Enable)
            {
                httpClientBuilder.AddTransientHttpErrorPolicy(policy =>
                    policy.CircuitBreakerAsync(
                        urlConfiguration.TransientHttpErrors.CircuitBreaker.MaximumFails,
                        TimeSpan.FromSeconds(urlConfiguration.TransientHttpErrors.CircuitBreaker.DurationInSeconds),
                        onBreak: (httpResponseMessage, timeSpan, context) =>
                        {
                            CircuitBreakerInfo circuitBreakerInfo = new CircuitBreakerInfo
                            {
                                EndPointName = urlConfiguration.Name,
                                TimeSpan = timeSpan
                            };

                            onCircuitBreakeOpened(circuitBreakerInfo, context);
                        },
                        onReset: (context) =>
                        {
                            onCircuitBreakeClosed(urlConfiguration.Name, context);
                        },
                        onHalfOpen: () =>
                        {
                        }
                    )
                );
            }
        }
    }

    public class RestApisUrl
    {
        public List<Url> Collection { get; set; } = new List<Url>();
    }

    public class Url
    {
        public string Name { get; set; } = string.Empty;
        public string Endpoint { get; set; } = string.Empty;
        public int TimeoutInSeconds { get; set; }
        public string Accept { get; set; } = string.Empty;
        public string Token { get; set; } = string.Empty;
        public List<Header> Headers { get; set; } = new List<Header>();
        public TransientHttpErrors TransientHttpErrors { get; set; } = new TransientHttpErrors();
    }

    public class Header
    {
        public string Name { get; set; } = string.Empty;
        public string Value { get; set; } = string.Empty;
    }

    public class TransientHttpErrors
    {
        public WaitAndRetry WaitAndRetryClassic { get; set; } = new WaitAndRetry();
        public WaitAndRetry WaitAndRetryExponential { get; set; } = new WaitAndRetry();
        public CircuitBreaker CircuitBreaker { get; set; } = new CircuitBreaker();
    }

    public class WaitAndRetry
    {
        public bool Enable { get; set; } = false;
        public short TotalRetry { get; set; } = 3;
        public int WaitMilliseconds { get; set; } = 1500;
    }

    public class CircuitBreaker
    {
        public bool Enable { get; set; } = false;
        public short MaximumFails { get; set; } = 6;
        public int DurationInSeconds { get; set; } = 10;
    }

    public class TransientHttpErrorInfo
    {
        public string EndPointName { get; set; }
        public string StatusCode { get; set; }
        public string ReasonPhrase { get; set; }
        public TimeSpan TimeSpan { get; set; }
        public int RetryCount { get; set; }
    }

    public class CircuitBreakerInfo
    {
        public string EndPointName { get; set; }
        public TimeSpan TimeSpan { get; set; }
    }

#pragma warning disable S3925 // "ISerializable" should be implemented correctly
    public class HttpClientsConfigurationException : ApplicationException
#pragma warning restore S3925 // "ISerializable" should be implemented correctly
    {
        public HttpClientsConfigurationException(string message) : base(message)
        {
        }
    }
}