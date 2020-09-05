package cl.jose.huenul.periodos.perdidos.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.jose.huenul.periodos.perdidos.app.constants.SwaggerUiConstants;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false).select()
				.apis(RequestHandlerSelectors.basePackage("cl.jose.huenul.periodos.perdidos.app.controller"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		ApiInfoBuilder apinfoBuilder = new ApiInfoBuilder();
		apinfoBuilder.title(SwaggerUiConstants.SWAGGER_UI_TITULO);
		apinfoBuilder.version(SwaggerUiConstants.SWAGGER_UI_VERSION);
		apinfoBuilder.description(SwaggerUiConstants.SWAGGER_UI_DESCRIPCION);
		apinfoBuilder.contact(new Contact(SwaggerUiConstants.SWAGGER_UI_CONTACTO_NOMBRE,
				SwaggerUiConstants.SWAGGER_UI_CONTACTO_PERSONAL_URL, SwaggerUiConstants.SWAGGER_UI_CONTACTO_MAIL));
		return apinfoBuilder.build();
	}
}
