package com.desafioPrevired.consumingRest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.desafioPrevired.consumingRest.model.PeriodoIn;


@SpringBootApplication
public class ConsumingRestApplication {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumingRestApplication.class);
	private static final String JSON_URL = "http://localhost:8080/periodos/api"; 

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
		
		LOG.info("Servicio busqueda de fechas faltantes levantado...");
	}

}
