
using DTO;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BO
{
    public class BO_EjemploType : IDisposable
    {
        public void Dispose() { }

        #region Variables
        bool bResultado = false;
        #endregion

        public bool BO_Ejemplo(ref DTO_EjemploType DTO_Ejemplo)
        {

            var dates = new List<DateTime>();

            for (var dt = DTO_Ejemplo.fechaCreacion; dt <= DTO_Ejemplo.fechaFin; dt = dt.AddMonths(1))
            {
                dates.Add(new DateTime(dt.Year, dt.Month, 1));
            }

            DTO_Ejemplo.fechasFaltantes = new List<string>();
            foreach (var item in dates)
            {
                if (!(DTO_Ejemplo.fechas.Where(x => Convert.ToDateTime(x) == item).ToList().Count() > 0))
                {
                    DTO_Ejemplo.fechasFaltantes.Add(item.ToString("yyyy-MM-dd"));
                }
               
            }

            DTO_Ejemplo.DTO_Transaction = new DTO_TransactionType();
            DTO_Ejemplo.DTO_Transaction.transactionNumber = "0";
            DTO_Ejemplo.DTO_Transaction.transactionMessage = "PROCESO REALIZADO CORRECTAMENTE";


            return bResultado;
        }
    }
}