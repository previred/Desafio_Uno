using BO;
using DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace WEBAPI_Ejemplo.Controllers
{
    [Authorize]
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class HomeController : ApiController    
    {
        #region Variables
        bool bResultado = false;
        #endregion

        [HttpPost]
        [Route("api/ListaEjemploPrevired")]
        public IHttpActionResult ListaEjemploPrevired(DTO_EjemploType DTO_Ejemplo)
        {
            try
            {
                using (BO_EjemploType BO_Ejemplo = new BO_EjemploType())
                {
                    bResultado = BO_Ejemplo.BO_Ejemplo(ref DTO_Ejemplo);
                }

                return Ok(DTO_Ejemplo);
            }
            catch (Exception ex)
            {
                return Ok(new DTO_EjemploType()
                {
                    DTO_Transaction = new DTO_TransactionType()
                    {
                        transactionNumber = "-180124090202",
                        transactionMessage = ex.Message
                    }
                });
            }

        }
    }
}
