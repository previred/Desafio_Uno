package com.previred.periodos.service.imp;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.periodos.error.InvalidInputException;
import com.previred.periodos.error.ResourceNotFoundException;
import com.previred.periodos.model.Periodo;
import com.previred.periodos.service.IGeneraDatosService;

@Service
public class GeneraDatosService implements IGeneraDatosService {

	private static final Logger logger = LoggerFactory.getLogger(GeneraDatosService.class);
			
	@Value("${rest.gdd.uri}")
	private String uriRestGDD;
	
	@Override
	public Periodo obtenerGDD() {
		
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setBufferRequestBody(false);

		RestTemplate restTemplate = new RestTemplate(factory);
		
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		// HttpEntity<String>: To get result as String.
		HttpEntity<String> entity = new HttpEntity<>(headers);
 
		try {
			// Send request with POST method, and Headers.
			ResponseEntity<Periodo> responseRest = restTemplate.exchange(uriRestGDD, HttpMethod.GET, entity, Periodo.class);
			
			if(responseRest.getStatusCodeValue() == 200){
				logger.info("Response for POST Request: OK ");
				
				Periodo result = responseRest.getBody();
				
				return result;
	
			}else{
				logger.info("Response for POST Request: NULL");
		        throw new ResourceNotFoundException("Error al invocar a GDD");
		    }
		
		} catch(Exception error) {
			 throw new ResourceNotFoundException(error.getMessage());
		}

	}

	@Override
	public Periodo obtenerFechasFaltantes(Periodo payload) {
		
		List<LocalDate> fechas = payload.getFechas();
		
		LocalDate start = payload.getFechaCreacion();
		LocalDate end = payload.getFechaFin();
		
		if(start.isBefore(end)) {
		
			List<LocalDate> periodosFaltantes = new ArrayList<LocalDate>();
			
			LocalDate tmp = start;
			while (tmp.isBefore(end) || tmp.isEqual(end)) {
				if(!fechas.contains(tmp))
					periodosFaltantes.add(tmp);
				tmp = tmp.plusMonths(1);
			}
						
			payload.setFechasFaltantes(periodosFaltantes);
			
			return payload;
		}else {
			throw new InvalidInputException("fechaCreacion es mayor a fechaFin");	
		}
		
	}

	@Override
	public Periodo obtenerGDDconFechasFaltantes() {
		
		Periodo result = new Periodo();
		
		result = this.obtenerGDD();
		result = this.obtenerFechasFaltantes(result);
		
		return result;
	}

}
