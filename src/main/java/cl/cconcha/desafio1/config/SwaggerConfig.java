package cl.cconcha.desafio1.config;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Autowired
	private TypeResolver typeResolver;
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).
				apiInfo(userApiInfo()).
				alternateTypeRules(AlternateTypeRules.newRule(
		                  typeResolver.resolve(List.class, LocalDate.class),
		                  typeResolver.resolve(List.class, Date.class), Ordered.HIGHEST_PRECEDENCE)).
				select().
				apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).
				build();
	}
	private ApiInfo userApiInfo() {
		return new ApiInfoBuilder().
				title("GDD Missing Dates Api").
				version("v1.0.0").
				license("Apache License Version 2.0").
				description("Api que obtiene los periodos faltantes en un rango de fechas aleatorio.").
				contact(new Contact("Carlos Concha", null, "carloscconcha@gmail.com")).
				build();
				
	}
}
