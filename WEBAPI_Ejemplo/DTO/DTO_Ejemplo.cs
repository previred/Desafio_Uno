using System;
using System.Collections.Generic;
using System.Text;

namespace DTO
{
    public class DTO_EjemploType
    {
        public int id { get; set; }
        public DateTime fechaCreacion { get; set; }
        public DateTime fechaFin { set; get; }
        public List<string> fechas { set; get; }
        public List<string> fechasFaltantes { set; get; }
        public DTO_TransactionType DTO_Transaction { set; get; }
    }
}