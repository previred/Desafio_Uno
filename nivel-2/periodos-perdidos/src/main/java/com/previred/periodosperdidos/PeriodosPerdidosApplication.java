package com.previred.periodosperdidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.previred.periodosperdidos"})
public class PeriodosPerdidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeriodosPerdidosApplication.class, args);
	}
	
	

}
