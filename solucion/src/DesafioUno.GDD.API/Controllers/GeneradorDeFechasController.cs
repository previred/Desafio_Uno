using DesafioUno.GDD.API.Models;
using DesafioUno.GDD.API.Process;
using Microsoft.AspNetCore.Mvc;

namespace DesafioUno.GDD.API.Controllers
{
    [Route("api/[controller]")]
    public class GeneradorDeFechasController : ApiController
    {
        [HttpPost("GeneraFechas")]
        public IActionResult GeneraFechas(DateTimeParameters dateTimeParameters)
        {
            if (dateTimeParameters.FechaCreacion < dateTimeParameters.FechaFin)
            {
                return !ModelState.IsValid ? CustomResponse(ModelState) : CustomResponse(GenerateRandomDates.GetRandomDates(dateTimeParameters));
            }
            else
            {
                AddError("Las fecha de inicio debe ser menor a la fecha de término");
                return CustomResponse();
            }
        }
    }
}
