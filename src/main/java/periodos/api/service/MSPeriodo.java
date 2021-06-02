package periodos.api.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import periodos.api.client.Periodo;
import periodos.api.client.PeriodoV2;
import periodos.api.client.PeriodosV1Client;

/**
 * Versión 2 del api período que indica el listado de fechas faltantes
 * @author Jorge Silva Borda
 */
@Path("/periodos/api/v2")
public class MSPeriodo {
    @Inject
    @RestClient
    private PeriodosV1Client periodosV1Client;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PeriodoV2 getPeriodoV2(){
        Periodo periodoV1 = periodosV1Client.getPeriodo();
        PeriodoV2 periodoV2 = new PeriodoV2();
        
        periodoV2.setId(periodoV1.getId());
        periodoV2.setFechaCreacion(periodoV1.getFechaCreacion());
        periodoV2.setFechaFin(periodoV1.getFechaFin());
        periodoV2.setFechas(periodoV1.getFechas());
        
        List<LocalDate> fechasFaltantes = new ArrayList();
        
        LocalDate fechaActual = periodoV1.getFechaCreacion();        
        
        
        while(fechaActual.isBefore(periodoV1.getFechaFin()) || fechaActual.isEqual(periodoV1.getFechaFin())){
            if(!periodoV1.getFechas().contains(fechaActual)){
                fechasFaltantes.add(fechaActual);
            }
            fechaActual = fechaActual.plusMonths(1);
        }
        periodoV2.setFechasFaltantes(fechasFaltantes);
        
        return periodoV2;
    }
    
}
