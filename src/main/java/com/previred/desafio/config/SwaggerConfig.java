package com.previred.desafio.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuracion para Swagger
 * @author Claudio Marambio Cespedes.
 * @since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.previred.desafio.controller"))
				.paths(PathSelectors.any())
				.build();
	}
}
