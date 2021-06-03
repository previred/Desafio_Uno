package cl.previred.periodos.client.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import cl.previred.periodos.client.GeneradorDeDatosClient;
import cl.previred.periodos.dto.PeriodoDTO;

@Component
public class GeneradorDeDatosClientImpl implements GeneradorDeDatosClient {
	
	public static Logger logger = LoggerFactory.getLogger(GeneradorDeDatosClientImpl.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Gson gson;
	
	
	@Value( "${generadordatos.url}" )
	private String gddUrl;
	
	@Override
	public PeriodoDTO obtenerPeriodosGeneradorDatos() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(gddUrl);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		logger.info(String.format("Realizando llamado GET a servicio REST generador de datos: %s"
				, gson.toJson(builder)));
		
		logger.debug(String.format("Headers: %s", gson.toJson(entity)));

		ResponseEntity<PeriodoDTO> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        PeriodoDTO.class);
		
		if(response.getStatusCode().equals(HttpStatus.OK)) {
			logger.info("Llamado a servicio Generador de Datos exitoso");
			logger.debug(String.format("Body: %s", gson.toJson(response.getBody())));
			return response.getBody();
		}
		
		logger.info(String.format("Servicio no respondio correctamente. Codigo: %s"
				,response.getStatusCode()));
		
		return null;
	}

}
