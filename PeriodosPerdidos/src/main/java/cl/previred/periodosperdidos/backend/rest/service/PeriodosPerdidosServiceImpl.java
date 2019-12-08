
package cl.previred.periodosperdidos.backend.rest.service;

import cl.previred.periodosperdidos.backend.rest.bean.Request;
import cl.previred.periodosperdidos.backend.rest.bean.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.swagger.annotations.Api;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Matias
 */
@Path("/previred")
@Api(value = "/previred")
@Service("periodosPerdidosService")
public class PeriodosPerdidosServiceImpl implements PeriodosPerdidosService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodosPerdidosServiceImpl.class);
    
    private static final String FORMATO_FECHA_GENERAL = "yyyy-MM-dd";
    
    @Value("${ws.endpoint.previred.api.gdd}")
    private String endpointGDD;
    
    @PostConstruct
    public void init() {
        LOGGER.info("..:: Iniciando servicio PeriodosPerdidosService ::..");
    }
    
    /**
    * Servicio REST de prueba para validar funcionamiento de los servicios.
    */
    @GET
    @Override
    @Path("/pivote")
    @Produces(MediaType.APPLICATION_JSON)
    public String pivote() {
        return "Hola mundo";
    }
    
    /**
    * Servicio REST - POST que genera la respuesta de los periodos perdidos.
    * Obtiene los datos necesarios para generar la respuesta llamando 
    * al servicio GDD.
    */
    @GET
    @Override
    @Path("/obtenerPeriodosPerdidos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPeriodosPerdidos() {
        try {
            Request input = this.generadorDeDatos();
            if (input == null) {
                LOGGER.error("Error, no se ingresaron datos en el request del "
                        + "metodo obtenerPeriodosPerdidos(). Input null.");
            }
            Response resp = new Response(input.getId(), input.getFechaCreacion()
                    , input.getFechaFin(), input.getFechas());

            List<String> fechasFaltantes = this.generaListaFechasFaltantes(input);
            resp.setFechasFaltantes(fechasFaltantes);
            
            return resp;
        } catch (Exception e) {
            LOGGER.error("Error al obtener Periodos Perdidos. {}", e);
            return null;
        }
    }
    
    /**
    * Metodo que calcula y genera la lista de fechas perdidas
    * que se necesitan entregar
    */
    public List<String> generaListaFechasFaltantes(Request input) {
        SimpleDateFormat formatDate = new SimpleDateFormat(FORMATO_FECHA_GENERAL);
        List<String> fechasFaltantes = new ArrayList<>();
        
        try {
            List<String> fechas = input.getFechas();
            
            Date dateInit = formatDate.parse(input.getFechaCreacion());
            Date dateEnd = formatDate.parse(input.getFechaFin());

            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dateInit);
            while(!calendario.getTime().after(dateEnd)) {
                String fechaPerdida = formatDate.format(calendario.getTime());
                fechasFaltantes.add(fechaPerdida);
                calendario.add(Calendar.MONTH, 1);
            }

            for (String fechaDelete : fechas) {
                fechasFaltantes.remove(fechaDelete);
            }
        } catch (Exception e) {
            LOGGER.error("Error al generar lista de fechas perdidas. {}", e);
        }
        
        return fechasFaltantes;
    }
    
    /**
    * Metodo que se encarga de llamar al servicio GDD de la api de previred
    * para obtener la lista de los datos a analizar.
    */
    @GET
    @Override
    @Path("/gdd")
    @Produces(MediaType.APPLICATION_JSON)
    public Request generadorDeDatos() {
        try {
            HttpResponse<String> response = Unirest.get(endpointGDD)
                    .header("cache-control", "no-cache")
                    .header("Accept", "application/json")
                    .asString();
            LOGGER.info("API Generador De Datos - Json Response: {}"
                    , response.getBody());
            Gson gson = new GsonBuilder().create();
            Request inputGDD = gson.fromJson(response.getBody(), Request.class);
            return inputGDD;
        } catch(JsonSyntaxException | UnirestException e) {
            LOGGER.error("Error al consultar servicio API GDD de previred. {}", e);
            return null;
        }
        
    }
    
}
