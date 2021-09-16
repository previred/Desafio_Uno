package cl.previred.prueba.tecnica.util.swagger;

import java.lang.reflect.WildcardType;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("cl.previred.prueba.tecnica.rest")).build()
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(AlternateTypeRules.newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.directModelSubstitute(java.time.OffsetDateTime.class, java.util.Date.class).apiInfo(getApiInfo());
	}

	/**
	 * 
	 * @return
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("API periodos response").description("Prueba para desafío Previred ")
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.termsOfServiceUrl("").version("1.0.0")
				.contact(new Contact("Josue Gajardo", "", "josue.gajardo@usach.cl")).build();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/swagger-ui/").setViewName("/swagger-ui/index.html");
	}

}