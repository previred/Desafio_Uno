package cl.previred.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2

@Configuration

public class SwaggerConfiguration {

	/**
	 * 
	 * Publish a bean to generate swagger2 endpoints
	 * 
	 * @return a swagger configuration bean
	 * 
	 */

	@Bean

	public Docket usersApi() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(usersApiInfo()).select().paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("cl.previred.app.controller")).build().useDefaultResponseMessages(false);

	}

	private ApiInfo usersApiInfo() {

		return new ApiInfoBuilder().title("Spring Boot REST API")
	            .description("Servicio que retorna periodos faltantes entre dos (2) desde un archivo de consulta")
	            .contact(new Contact("Jose Alberto Valdez Padron","","jvaldez.padron@gmail.com"))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
	}

}
