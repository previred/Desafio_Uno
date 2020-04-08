package com.previred.periodos.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author arojas
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket detalheApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("com.previred.periodos.backend")).paths(PathSelectors.any())
				.build().apiInfo(this.informacoesApi().build());

		return docket;
	}

	private ApiInfoBuilder informacoesApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("Api-Periodos-Backend");
		apiInfoBuilder.description("Api para recuperar periodos de fechas faltantes");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Terminos de uso: Test de conocimientos para cargo Ingeniero Desarrollado");
		apiInfoBuilder.license("Licencia - Open Source");
		apiInfoBuilder.contact(this.contacto());

		return apiInfoBuilder;
	}

	private Contact contacto() {
		return new Contact("Alex Francisco Rojas Perez", "arojas", "alex.rojas.perez@gmail.com");
	}
}
