using System.Collections.Generic;
using System.ComponentModel;

namespace DesafioUno.GDD.API.Models
{
    public class DateTimeGenerated
    {
        public DateTimeGenerated(
            int id,
            string fechaCreacion,
            string fechaFin,
            IList<string> fechasGeneradas
            )
        {
            Id = id;
            FechaCreacion = fechaCreacion;
            FechaFin = fechaFin;
            FechasGeneradas = fechasGeneradas;
        }


        [DisplayName("id")]
        public int Id { get; set; }

        [DisplayName("fechaCreacion")]
        public string FechaCreacion { get; set; }

        [DisplayName("fechaFin")]
        public string FechaFin { get; set; }

        [DisplayName("fechas")]
        public IList<string> FechasGeneradas { get; set; }
    }
}
