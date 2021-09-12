using System;

namespace PeriodosPerdidos.Abstractions.Repositories
{
    public interface IDateService //declaro interfaz
    {
        DateTime[] getMissedPeriods(DateTime initial, DateTime finished);

        DateTime[] MaximunDates(DateTime[] dateTimes);
    }
}