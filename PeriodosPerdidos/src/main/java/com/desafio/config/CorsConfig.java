package com.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración Cors en caso que se requiera consumir desde un cliente
 * @author alejandro.cabezas05@gmail.com
 */
@Configuration
public class CorsConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:9001")
				.allowedMethods("GET","POST","PUT","DELETE")
				.maxAge(3600);
			}
		};
	}
}
