package com.api_desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.api_desafio1"})
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		
		Class<?>[] configClasses = {ApiApplication.class, Swagger2Configuracion.class};
		
		SpringApplication.run(configClasses, args);
	}
}
