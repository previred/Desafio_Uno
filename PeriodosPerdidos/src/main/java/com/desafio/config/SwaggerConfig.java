package com.desafio.config;

import java.time.LocalDate;
import java.util.List;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import com.fasterxml.classmate.TypeResolver;
/**
 * Configuración swagger
 * @author alejandro.cabezas05@gmail.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//variable para configurar la información que muestra swagger
	ApiInfo apiInformation() {
	        return new ApiInfoBuilder()
	            .title("API Periodos Perdidos")
	            .description("Este servicio REST invoca al servicio GDD y entrega una respuesta en formato JSON con las fechas recibidas(fechaCreacion y fechaFin) y las fechas faltantes.")
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .termsOfServiceUrl("")
	            .version("0.0.1-SNAPSHOT")
	            .contact(new Contact("","", "alejandro.cabezas05@gmail.com"))
	            .build();
	    }
	 
	@Bean
	public Docket api() {		
		//se agrega una regla para resolver el conflicto con el tipo de datos LocalDate
		 TypeResolver typeResolver = new TypeResolver();
		return new Docket(DocumentationType.SWAGGER_2)						
				.alternateTypeRules(AlternateTypeRules.newRule( 						
						typeResolver.resolve(List.class, LocalDate.class),  
						typeResolver.resolve(List.class, String.class), Ordered.HIGHEST_PRECEDENCE))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.desafio.controller"))
				.paths(PathSelectors.any())
				.build()				
				.apiInfo(apiInformation());
	}
}
