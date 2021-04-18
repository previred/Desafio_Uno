package com.springboot.periodos.perdidos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author crist
 */

@Configuration
public class AppConfig {

	/**
	 * cliente rest
	 * @return
	 */
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
