
package cl.previred.periodosperdidos.backend.rest.service;

import cl.previred.periodosperdidos.backend.rest.bean.Request;
import cl.previred.periodosperdidos.backend.rest.bean.Response;

/**
 *
 * @author Matias
 */
public interface PeriodosPerdidosService {
    
    public String pivote();

    public Response obtenerPeriodosPerdidos();
    
    public Request generadorDeDatos();

}
