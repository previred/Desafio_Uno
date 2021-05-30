package cl.cmoscoso.config;

import java.time.LocalDate;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)//
				.directModelSubstitute(LocalDate.class, java.util.Date.class)
				.select()//
				.apis(RequestHandlerSelectors.basePackage("cl.cmoscoso"))//
				.paths(PathSelectors.any())//
				.build()//
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact contact = new Contact("Claudio Moscoso", "https://www.linkedin.com/in/claudiomoscoso/",
				"claudio.moscoso@gmail.com");
		ApiInfo apiInfo = new ApiInfo("API-Rest para analisar GDD y complementar fechas faltantes", " ", "1.0.0",
				"Terminos del servicio", contact, "Uso interno", "", Collections.emptyList());
		return apiInfo;
	}
}
