package com.previred.desafio.bjimenez;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = { "com.previred.desafio" })
@SpringBootApplication()
@EnableAutoConfiguration
public class DesafioNivel3Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioNivel3Application.class, args);
	}

}
