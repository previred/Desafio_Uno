package com.desafio.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author alejandro.cabezas05@gmail.com
 */
@SpringBootApplication
@ComponentScan(basePackages = { 
		"com.desafio.config", 
	    "com.desafio.controller",
	    "com.desafio.main",
	    "com.desafio.object",
	    "com.desafio.service",
		"com.desafio.util"})
public class PeriodosPerdidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeriodosPerdidosApplication.class, args);
	}

}
