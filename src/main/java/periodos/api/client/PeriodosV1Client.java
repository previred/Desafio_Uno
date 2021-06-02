package periodos.api.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * Interfaz para cliente de la api periodos incompleta
 * @author Jorge Silva Borda 
 */
@RegisterRestClient(configKey = "config.api.periodos-v1")
@Path("")
public interface PeriodosV1Client {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Periodo getPeriodo();
}
