package cl.pvr.fechas.faltantes.config;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	//Configuración de Swagger
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cl.pvr.fechas.faltantes.controller"))
				.build()
				.directModelSubstitute(LocalDate.class, java.sql.Date.class)
	            .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
	            .useDefaultResponseMessages(false)
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Obtener Fechas faltantes")
	            .description("Servicio que detecta fechas faltantes entre un 2 fechas determinadas")
	            .license("1.0")
	            .licenseUrl("")
	            .termsOfServiceUrl("")
	            .version("1.0.0")
	            .contact(new Contact("Christian Acuña", "", "christianhal93@gmail.com"))
	            .build();
			
	}
}
