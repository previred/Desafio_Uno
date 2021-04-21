package cl.previred.desafio.uno.config;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();
        AlternateTypeRule collectionRule = AlternateTypeRules.newRule(
                typeResolver.resolve(List.class, LocalDate.class),
                typeResolver.resolve(ArrayList.class, Date.class));
        
		return new Docket(DocumentationType.SWAGGER_2)
				.alternateTypeRules(collectionRule)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cl.previred.desafio.uno"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"API",
				"API Description",
				"1.0",
				"http://localhost:8080",
				new Contact("Test", "https://google.com", "test@test.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList());
	}
}