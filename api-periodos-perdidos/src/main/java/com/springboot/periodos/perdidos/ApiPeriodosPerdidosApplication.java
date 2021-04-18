package com.springboot.periodos.perdidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author crist
 *
 */

@EnableFeignClients
@SpringBootApplication
public class ApiPeriodosPerdidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPeriodosPerdidosApplication.class, args);
	}

}
