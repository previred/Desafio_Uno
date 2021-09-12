using Microsoft.Extensions.Logging;
using System;

namespace PeriodosPerdidos.Common.Helper
{
    public class BaseServiceContext
    {
        public ILogger Logger { get; set; }

        public Guid ApplicationId { get; set; }
        public string ActivityId { get; set; }
        public string Source { get; set; }

        public BaseServiceContextSesionAndUser SesionAndUser { get; set; }
        public BaseServiceContextDependency Dependency { get; set; }

        public BaseServiceContext(ILogger logger, Guid applicationId, string activityId, string source, BaseServiceContextDependency dependency)
        {
            Logger = logger;
            ApplicationId = applicationId;
            ActivityId = activityId;
            Source = source;
            Dependency = dependency ?? throw new ArgumentNullException(nameof(dependency));
            SesionAndUser = new BaseServiceContextSesionAndUser();
        }

        public BaseServiceContext(ILogger logger, Guid applicationId, string activityId, string source, BaseServiceContextDependency dependency, BaseServiceContextSesionAndUser sesionAndUser)
        {
            Logger = logger;
            ApplicationId = applicationId;
            ActivityId = activityId;
            Source = source;
            Dependency = dependency ?? throw new ArgumentNullException(nameof(dependency));
            SesionAndUser = sesionAndUser ?? throw new ArgumentNullException(nameof(sesionAndUser));
        }
    }

    public class BaseServiceContextSesionAndUser
    {
        public string SessionId { get; set; } = string.Empty;
        public string UserId { get; set; } = string.Empty;
    }

    public class BaseServiceContextDependency
    {
        public string TypeName { get; set; } = string.Empty;
        public string Name { get; set; } = string.Empty;
        public string Target { get; set; } = string.Empty;
    }
}
