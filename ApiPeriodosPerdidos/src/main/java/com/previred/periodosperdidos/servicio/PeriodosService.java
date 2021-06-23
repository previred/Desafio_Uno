package com.previred.periodosperdidos.servicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.previred.periodosperdidos.swagger.codegen.model.PeriodoPerdido;


/**
 *
 * @author csalce@gmail.com
 */
@Service
public class PeriodosService {

	private final static Logger LOGGER = Logger.getLogger(PeriodosService.class.getName());
    
    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public PeriodoPerdido getPeriodos() {
    	
    	// Invocación de GDD!...
    	Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
    	WebTarget webTarget = client.target("http://localhost:8080/periodos/api");
    	 
    	Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
    	Response response = invocationBuilder.get();
    	String message = response.readEntity(String.class);
    	
    	
    	// Transformación de respuesta de servicio en JSON!...
    	JSONObject jsonObject = new JSONObject(message);

    	
    	// Preparación de datos para proceso!.. 
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	PeriodoPerdido periodoPerdido = new PeriodoPerdido();

    	periodoPerdido.setId(jsonObject.getLong("id"));
        periodoPerdido.setFechaCreacion(LocalDate.parse(jsonObject.getString("fechaCreacion"), dtf));
        periodoPerdido.setFechaFin(LocalDate.parse(jsonObject.getString("fechaFin"), dtf));

        java.util.List<Object> listFechas = jsonObject.getJSONArray("fechas").toList();
        Set<LocalDate> fechas = new HashSet();
    	for (Iterator<Object> iterator = listFechas.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
			fechas.add(LocalDate.parse(object, dtf));
		}  	
        periodoPerdido.setFechas(fechas.stream()
                	  .sorted()
                	  .collect(Collectors.toList()));
    	periodoPerdido.setFechasFaltantes(null);

    	
        // Detección de periodos faltantes!...
        long monthsToProcess = ChronoUnit.MONTHS.between(periodoPerdido.getFechaCreacion(), periodoPerdido.getFechaFin())+1; // +1 para incluir el último valor!...
        int yearProcess = periodoPerdido.getFechaCreacion().getYear(); 
        int monthProcess = periodoPerdido.getFechaCreacion().getMonthValue();

        System.out.println("*********************************");
        System.out.println("monthsToProcess:" + monthsToProcess);
        System.out.println("yearProcess:" + yearProcess);
        System.out.println("monthProcess:" + monthProcess);
        System.out.println("*********************************");
        
        for (int i = 1; i<=monthsToProcess; i++) {
        	
        	// Validación de fecha faltante!...
        	LocalDate dateProcess = LocalDate.of(yearProcess, monthProcess, 1);
            System.out.println("Finding " + dateProcess);
            Optional<LocalDate> queryResult = fechas.stream()
                    .filter(value -> value != null)
                    .filter(value -> value.equals(dateProcess))
                    .findFirst();
            if (!queryResult.isPresent()) {
            	periodoPerdido.addFechasFaltantesItem(dateProcess);
            }
        	
        	// Control de avance de proceso!...
        	monthProcess++;
        	if (monthProcess > 12) {
        		yearProcess++;
        		monthProcess = 1;
        	}
        }
        
        System.out.println("*********************************");
        System.out.println(periodoPerdido.toString());
        System.out.println("*********************************");

        return periodoPerdido;
    }
}
