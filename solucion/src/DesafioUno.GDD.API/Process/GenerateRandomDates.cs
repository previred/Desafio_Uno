using DesafioUno.GDD.API.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace DesafioUno.GDD.API.Process
{
    public static class GenerateRandomDates
    {
        public static DateTimeGenerated GetRandomDates(DateTimeParameters dateTimeParameters)
        {
            int quantityOfDates = CalculateQuantityOfDatesToGenerate(dateTimeParameters.FechaCreacion, dateTimeParameters.FechaFin);
            string newDate = string.Empty;
            bool existDate;

            IList<string> generatedDates = new List<string>();

            for (int item = 1; item <= quantityOfDates; item++)
            {
                existDate = true;
                while (existDate)
                {
                    newDate = GenerateRandomDate(dateTimeParameters.FechaCreacion, dateTimeParameters.FechaFin);
                    existDate = generatedDates.Contains(newDate);
                }

                generatedDates.Add(newDate);
            }

            return new DateTimeGenerated(
                    1,
                    dateTimeParameters.FechaCreacion.ToString("yyy-MM-dd"),
                    dateTimeParameters.FechaFin.ToString("yyy-MM-dd"),
                    generatedDates.OrderBy(q => q).ToList()
                );
        }

        private static int CalculateQuantityOfDatesToGenerate(DateTime initialDate, DateTime endingDate)
        {
            var random = new Random();
            int quantity = random.Next(1, CalculateMaximunPossibleItems(initialDate, endingDate));
            if (quantity > 1)
            {
                quantity--;
            }
            return quantity;
        }

        private static int CalculateMaximunPossibleItems(DateTime initialDate, DateTime endingDate)
        {
            int quantityOfItems = 0;

            if (endingDate.Year == initialDate.Year)
            {
                quantityOfItems = endingDate.Month - initialDate.Month;
            }
            else
            {
                if ((endingDate.Year - initialDate.Year) > 1)
                {
                    quantityOfItems = (endingDate.Year - initialDate.Year) * 12;
                }

                quantityOfItems += 12 - initialDate.Month;
                quantityOfItems += endingDate.Month;
            }

            return quantityOfItems;
        }

        private static string GenerateRandomDate(DateTime initialDate, DateTime endingDate)
        {
            var random = new Random();

            int year = random.Next(initialDate.Year, endingDate.Year + 1);

            int initialMonth = year.Equals(initialDate.Year) ? initialDate.Month : 1;
            int endingMonth = year.Equals(endingDate.Year) ? endingDate.Month : 12;

            int month = random.Next(initialMonth, endingMonth + 1);

            return $"{year}-{month:00}-01";
        }
    }
}
