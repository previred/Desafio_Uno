package com.previred.periodos.cliente;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.previred.periodos.swagger.codegen.model.Periodo;

public class RestClientHelper {
	
	private static final String HOST = "http://127.0.0.1:8080";
	private RestTemplate restTemplate = CustomRestTemplate.getRestTemplate();
	
	public Periodo gePeriodo() throws HttpClientErrorException {
		Periodo periodo = null;
		String url = HOST + "/periodos/api";
		periodo = restTemplate.getForObject(url, Periodo.class);
		return periodo;
	}

}
