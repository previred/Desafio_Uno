using PeriodosPerdidos.Business.Commands.Date;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Business.Processors.Date
{
    public class Evaluate : MediatR.IRequestHandler<Commands.Date.Evaluate, Commands.Date.EvaluateResponse> //defino el procesador, indico los comando de entrada y el resultado
    {
        private readonly Abstractions.Repositories.IDateService _dateService; //defino la interfaz para ejecutar servicio de fechas

        //en el constructor solicito que me entregue la implementacion de la interfaz, esto lo configuramos en el startup, 
        //puede darse que una interfaz tenga mas de una implementacion, en ese caso se pide una lista de interfaces, me entrega todas las implementaciones, hay muchos juegos que se pueden hacer
        public Evaluate(Abstractions.Repositories.IDateService dateService)
        {
            _dateService = dateService;
        }

        public Task<EvaluateResponse> Handle(Commands.Date.Evaluate request, CancellationToken cancellationToken)
        {
            //se ejecuta el procesador 
            EvaluateResponse result = new();

            //validamos los datos de entrada
            FluentValidation.Results.ValidationResult validationResult = (new Commands.Date.EvaluateValidator()).Validate(request);
            if (!validationResult.IsValid)
            {
                result.Success = false;
                result.ReasonPhrase = string.Join(", ", validationResult.Errors);
                return Task.FromResult(result);
            }

            //convertimos los datos // aqui uno esta seguro de la conversion, anteriormente se validaron
            _ = DateTime.TryParse(request.FechaCreacion, out DateTime fechaCreacion);
            _ = DateTime.TryParse(request.FechaFin, out DateTime fechaFin);
            List<DateTime> fechas = new List<DateTime>();
            foreach (string fecha in request.Fechas)
            {
                _ = DateTime.TryParse(fecha, out DateTime resultParse);
                fechas.Add(resultParse);
            }

            //esta validacion la debi haber hecho en fluentvalidation, no alcance por tiempo
            if (fechaCreacion > fechaFin)
            {
                result.Success = false;
                result.ReasonPhrase = "La fecha creacion no puede ser mayor a la fecha fin";
                return Task.FromResult(result);
            }

            //ejecuto los metodos de la interfaz que solicite
            DateTime[] missedPeriods = _dateService.getMissedPeriods(fechaCreacion, fechaFin);

            //validar las fechas ingresadas que sean validas
            //

            //elimino las fechas que informaron inicialmente
            missedPeriods = missedPeriods.Where(d => !fechas.Exists(f => f == d)).ToArray();

            //si tengo mas de 100 resultados ejecuto una funcion del respositorio
            if (missedPeriods.Length >= 100)
                missedPeriods = _dateService.MaximunDates(missedPeriods);

            //preparo la salida de la informacion
            result.Id = request.Id;
            result.FechaCreacion = fechaCreacion;
            result.FechaFin = fechaFin;
            result.FechasFaltantes = missedPeriods;

            result.Success = true;

            return Task.FromResult(result);
        }
    }
}