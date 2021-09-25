using DesafioUno.GDD.API.Models;
using DesafioUno.Nivel3.API.Models;
using DesafioUno.Nivel3.API.Process;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Options;
using Newtonsoft.Json;

namespace DesafioUno.Nivel3.API.Controllers
{
    [Route("api/[controller]")]
    public class ObtainDateRangeController : ApiController
    {
        private readonly IOptions<APIParameters> _APIParameters;

        public ObtainDateRangeController(IOptions<APIParameters>aAPIParameters)
        {
            _APIParameters = aAPIParameters;
        }

        [HttpPost("ObtieneRangoDeFechas")]
        public IActionResult ObtieneFechas(DateTimeParameters dateTimeParameters)
        {
            var responseJson = ApiCall.PostApi(_APIParameters.Value.GDD, JsonConvert.SerializeObject(dateTimeParameters));

            var dateTimeComplemented = AddMisingDates.GetRandomDates(JsonConvert.DeserializeObject<DateTimeGenerated>(responseJson));

            return !ModelState.IsValid ? CustomResponse(ModelState) : CustomResponse(dateTimeComplemented);
        }
    }
}
