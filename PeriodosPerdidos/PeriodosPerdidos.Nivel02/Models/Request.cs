using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PeriodosPerdidos.Nivel02.Models
{
    public class Request
    {
        public int Id { get; set; } 
        public string FechaCreacion { get; set; }
        public string FechaFin { get; set; }
        public string[] Fechas { get; set; }
    }
}