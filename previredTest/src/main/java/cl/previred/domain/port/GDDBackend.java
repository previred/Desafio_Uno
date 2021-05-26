package cl.previred.domain.port;


import cl.previred.domain.error.InternalBussinesException;
import cl.previred.domain.model.PeriodoResult;
/**
 * Servicio que provee de operaciones para resolución de fechas
 * 
 * @author wmunoz
 *
 */
public interface GDDBackend {

    PeriodoResult getRandomData()throws InternalBussinesException;
    
}
