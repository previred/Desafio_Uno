package com.previred.vc.mejoraperiodos.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).
				select()
				.apis(RequestHandlerSelectors.basePackage("com.previred.vc.mejoraperiodos.api"))
				.build()
				.apiInfo(metaInfo());

	}

	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo("API Periodos Faltantes", 
				"Spring Boot para Previred que Calcula Periodos Faltantes", 
				"1.0", 
				"Terms of Service",
				new Contact("Victor Cifuentes", null, "vcifuentes@gmail.com"), 
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/", 
				new ArrayList<>());

		return apiInfo;
	}

}
