using MediatR;
using Microsoft.AspNetCore.Mvc;
using System.Linq;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Controllers
{
    [Route("ServicioFechas")]
    [ApiController]
    public class Dates : ControllerBase
    {
        private readonly IMediator _mediator; //declaramos una variable privada y de solo lectura

        public Dates(IMediator mediator) //al constructor le pedimos la interfaz de mediator, la aplicacion ya save la implementacion, ya viene implementada
        {
            _mediator = mediator;
        }

        //declaramos el recurso del servicio
        [HttpPost("Evaluar")]
        public async Task<IActionResult> Evaluar(Business.Commands.Date.Evaluate request) // obtenemos los parametros de entrada
        {
            Business.Commands.Date.EvaluateResponse response = await _mediator.Send(request); //entregamos la informacion a mediator, el se encarga de entregarle al procesador de negocio la informacion

            if (!response.Success) //si mediator responde que me fue mal, respondo bad request y la razon por que me fue mal
                return BadRequest(response.ReasonPhrase);

            return Ok(new Common.Models.ResultPeriods()
            {
                Id = response.Id,
                FechaCreacion = response.FechaCreacion.ToString("yyyy-MM-dd"),
                FechaFin = response.FechaFin.ToString("yyyy-MM-dd"),
                FechasFaltantes = response.FechasFaltantes.Select(ff => ff.ToString("yyyy-MM-dd")).ToArray()
        }); // si me fue bien en mediator, respondo 200 y el resultado lo convierto a un modelo esperado
        }
    }
}