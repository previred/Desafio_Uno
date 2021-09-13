package com.saros.prueba.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.saros.prueba"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder ()
						.version("1.0.0")
						.title ("Prueba Servicio")
						.contact (new Contact("Sebastian Aros", "", "aros.sebastian@gmail.com"))
						.description ("Prueba de Consumo Servicio de Fechas")
						.build());
				
	}
	

}