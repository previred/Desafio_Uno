package cl.previred.desafio.uno.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.previred.desafio.uno.dto.GDDApiPeriodResponse;

@Service
public class GDDService {
	@Autowired
	private RestTemplate restTemplate;
	
	public GDDApiPeriodResponse getDates() {
		return restTemplate.getForObject("http://localhost:8080/periodos/api", GDDApiPeriodResponse.class);
	}
}
