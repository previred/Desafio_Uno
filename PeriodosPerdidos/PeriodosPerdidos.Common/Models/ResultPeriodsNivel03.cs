using System.Linq;

namespace PeriodosPerdidos.Common.Models
{
    public class ResultPeriodsNivel03
    {
        public int Id { get; set; }
        public string FechaCreacion { get; set; }
        public string FechaFin { get; set; }
        public string[] Fechas { get; set; }
        public string[] FechasFaltantes { get; set; }

        //public ResultPeriodsNivel03(Business.Commands.Nivel03.GddResponse gddResponse)
        //{
        //    this.Id = gddResponse.Id;
        //    this.FechaCreacion = gddResponse.FechaCreacion.ToString("yyyy-MM-dd");
        //    this.FechaFin = gddResponse.FechaFin.ToString("yyyy-MM-dd");
        //    this.Fechas = gddResponse.Fechas.Select(ff => ff.ToString("yyyy-MM-dd")).ToArray();
        //    this.FechasFaltantes = gddResponse.FechasFaltantes.Select(ff => ff.ToString("yyyy-MM-dd")).ToArray();
        //}
    }
}