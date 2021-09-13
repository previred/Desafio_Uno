package com.saros.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author saros
 *
 */
@SpringBootApplication
public class PruebaServicioGDDRest {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate ();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(PruebaServicioGDDRest.class, args);
	}

}
