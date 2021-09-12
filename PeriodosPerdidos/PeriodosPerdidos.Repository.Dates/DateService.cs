using System;
using System.Collections.Generic;
using System.Linq;

namespace PeriodosPerdidos.Repository.Dates
{
    public class DateService : Abstractions.Repositories.IDateService //implemento la interfaz
    {
        public DateTime[] getMissedPeriods(DateTime initial, DateTime finished) //construyo la logica de las fechas
        {
            List<DateTime> result = new(); //creo una lista con todas las fechas desde el inicio hasta el final

            result.Add(initial);
            while (finished > initial)
            {
                initial = initial.AddDays(1);
                result.Add(initial);
            }

            return result.Where(d => d.Day == 1).ToArray(); //retorno todas las fechas que el dia sea igual a 1
        }

        public DateTime[] MaximunDates(DateTime[] dateTimes) // si el arreglo de fechas es mayor a 100, borro aleatoriamente registros del arreglo hasta que tenga 100 registros
        {
            int toDelete = dateTimes.Length - 100;
            if (toDelete <= 0)
                return dateTimes;

            Random rand = new();
            List<DateTime> result = dateTimes.ToList();

            while (result.Count > 100)
            {
                result.RemoveAt(rand.Next(0, result.Count));
            }

            return result.ToArray();
        }
    }
}