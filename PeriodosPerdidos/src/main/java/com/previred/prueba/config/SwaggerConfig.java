package com.previred.prueba.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.previred.prueba.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Api para el calculo de fechas faltantes en GDD")
				.version("v1.0")
				.license("Apache License Version 2.0")
				.termsOfServiceUrl("#")
				.build();
	}
}
