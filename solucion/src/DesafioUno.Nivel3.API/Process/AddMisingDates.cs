using DesafioUno.GDD.API.Models;
using DesafioUno.Nivel3.API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DesafioUno.Nivel3.API.Process
{
    public static class AddMisingDates
    {
        public static DateTimeComplemented GetRandomDates(DateTimeGenerated dateTimeGenerated)
        {
            var random = new Random();
            int quantityOfDates = CalculateMaximunPossibleItems(DateTime.Parse(dateTimeGenerated.FechaCreacion), DateTime.Parse(dateTimeGenerated.FechaFin));
            int yearStart = DateTime.Parse(dateTimeGenerated.FechaCreacion).Year;
            int monthStart = DateTime.Parse(dateTimeGenerated.FechaCreacion).Month;
            string newDate = string.Empty;
            bool existDate;

            IList<string> generatedMissingDates = new List<string>();

            for (int item = 1; item <= quantityOfDates; item++)
            {
                newDate = $"{yearStart}-{monthStart:00}-01";
                if (!dateTimeGenerated.FechasGeneradas.Contains(newDate))
                {
                    generatedMissingDates.Add(newDate);
                }
                monthStart++;
                if (monthStart>12)
                {
                    yearStart++;
                    monthStart = 1;
                }
            }

            return new DateTimeComplemented(
                    dateTimeGenerated.Id,
                    dateTimeGenerated.FechaCreacion,
                    dateTimeGenerated.FechaFin,
                    dateTimeGenerated.FechasGeneradas,
                    generatedMissingDates.OrderBy(q => q).ToList()
                );
        }

        public static int CalculateMaximunPossibleItems(DateTime initialYear, DateTime endingYear)
        {
            int quantityOfItems = (endingYear.Year - initialYear.Year) * 12;
            quantityOfItems -= (initialYear.Month - 1);
            quantityOfItems += endingYear.Month;

            return quantityOfItems;
        }
    }
}
