namespace PeriodosPerdidos.Nivel02.Models
{
    public class ResultPeriods
    {
        public int Id { get; set; }
        public string FechaCreacion { get; set; }
        public string FechaFin { get; set; }
        public string[] FechasFaltantes { get; set; }
    }
}