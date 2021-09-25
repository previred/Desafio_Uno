using System.Collections.Generic;
using System.ComponentModel;

namespace DesafioUno.Nivel3.API.Models
{
    public class DateTimeComplemented
    {
        public DateTimeComplemented(
            int id,
            string fechaCreacion,
            string fechaFin,
            IList<string> fechasGeneradas,
            IList<string> fechasFaltantes
            )
        {
            Id = id;
            FechaCreacion = fechaCreacion;
            FechaFin = fechaFin;
            FechasGeneradas = fechasGeneradas;
            FechasFaltantes = fechasFaltantes;
        }


        [DisplayName("id")]
        public int Id { get; set; }

        [DisplayName("fechaCreacion")]
        public string FechaCreacion { get; set; }

        [DisplayName("fechaFin")]
        public string FechaFin { get; set; }

        [DisplayName("fechasGeneradas")]
        public IList<string> FechasGeneradas { get; set; }

        [DisplayName("fechasFaltantes")]
        public IList<string> FechasFaltantes { get; set; }
    }
}
