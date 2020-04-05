package com.previred.fechas.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.previred"))
				.paths(regex("/api.*")).build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {

		return new ApiInfoBuilder().title("Documentacion Desafío 1: Periodos perdidos")
				.description("API REST que consume servicio Previred, recibe json con fechas dentro de un periodo, determina fechas"
						+ " que faltan en el periodo e informa Fechas recibidas y fechas faltantes").version("1.0").build();

	}
}