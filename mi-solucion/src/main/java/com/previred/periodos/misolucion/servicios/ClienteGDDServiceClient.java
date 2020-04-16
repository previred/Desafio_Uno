/**
 * 
 */
package com.previred.periodos.misolucion.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.previred.periodos.misolucion.dominio.Periodo;

/**
 * @author Leonardo Silva Bustos
 *
 */
@Service
public class ClienteGDDServiceClient implements ClienteGDDService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteGDDServiceClient.class);
	
	private final RestTemplate restTemplate;
	
	private final String endpoint;
	/**
	 * 
	 */
	public ClienteGDDServiceClient(RestTemplate restTemplate, @Value("${gdd-service.endpoint}") String endpoint) {
		this.restTemplate = restTemplate;
		this.endpoint = endpoint;
	}
	
	public Periodo consultaService() {
		Periodo periodo = null;
		try {
			LOGGER.info("llamando al servicio en la url: {}", this.endpoint);
			ResponseEntity<Periodo> result = restTemplate.getForEntity(this.endpoint, Periodo.class);
			if(result != null) {
				LOGGER.info("Se ha recibido respuesta");
				periodo = result.getBody();
			}
		} catch (RestClientException e) {
			LOGGER.error("Error cuando se consume el servicio: ", e.getMessage());
		}
		LOGGER.info("El periodo: {}", periodo);
		return periodo;
			
	}
}
