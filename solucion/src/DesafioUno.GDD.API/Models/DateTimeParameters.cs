using System;
using System.ComponentModel.DataAnnotations;

namespace DesafioUno.GDD.API.Models
{
    public class DateTimeParameters
    {
        [Required]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd}")]
        [DataType(DataType.Date, ErrorMessage = "La fecha informada no cumple con el formato especificado")]
        public DateTime FechaCreacion { get; set; }

        [Required]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd}")]
        [DataType(DataType.Date, ErrorMessage = "La fecha informada no cumple con el formato especificado")]
        public DateTime FechaFin { get; set; }
    }
}
