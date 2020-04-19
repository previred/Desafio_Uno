package io.ajsoft.previred.desafio1.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.ajsoft.previred.desafio1.dto.InObjectDTO;

@Component
public class GDDClientHelper {
	
	@Value("${io.ajsoft.previred.gdd-url:http://localhost:8080/periodos/api}")
	String urlGDD;	
	
	public InObjectDTO callGDD() {
		RestTemplate rest = new RestTemplate();
		InObjectDTO to = rest.getForObject(urlGDD, InObjectDTO.class);
		return to;
	}

}
