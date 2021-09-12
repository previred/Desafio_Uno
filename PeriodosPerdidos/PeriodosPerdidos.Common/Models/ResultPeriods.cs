using System.Linq;

namespace PeriodosPerdidos.Common.Models
{
    public class ResultPeriods // declaro una clase con propiedades publicas,  en el constructor de la clase seteo la informacion
    {
        public int Id { get; set; }
        public string FechaCreacion { get; set; }
        public string FechaFin { get; set; }
        public string[] FechasFaltantes { get; set; }

        public ResultPeriods() { }

        //public ResultPeriods(Business.Commands.Date.EvaluateResponse evaluateResponse)
        //{
        //    this.Id = evaluateResponse.Id;
        //    this.FechaCreacion = evaluateResponse.FechaCreacion.ToString("yyyy-MM-dd");
        //    this.FechaFin = evaluateResponse.FechaFin.ToString("yyyy-MM-dd");
        //    this.FechasFaltantes = evaluateResponse.FechasFaltantes.Select(ff => ff.ToString("yyyy-MM-dd")).ToArray();
        //}
    }
}