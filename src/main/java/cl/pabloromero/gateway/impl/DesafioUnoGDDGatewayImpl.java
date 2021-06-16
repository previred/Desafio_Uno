package cl.pabloromero.gateway.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.pabloromero.gateway.DesafioUnoGDDGateway;
import cl.pabloromero.model.Periodos;

@Component
public class DesafioUnoGDDGatewayImpl implements DesafioUnoGDDGateway {

	@Value("${api.periodos}")
	private String urlPeriodos;

	@Autowired
	private RestTemplate restTemplate;
	
	public Periodos periodos() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("", headers);

		ResponseEntity<Periodos> response = restTemplate.exchange(urlPeriodos, HttpMethod.GET, entity, Periodos.class);

		return response.getBody();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
