package com.previred.periodosperdidos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
