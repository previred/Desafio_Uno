package cl.previred.desafio.uno.nivel3.api.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {
	@Value(SwaggerApiDocConstants.TAG_MCERDA_PERIODOS)
	private String tagPeriodos;
	@Value(SwaggerApiDocConstants.TAG_DESCRIPTION_MCERDA_PERIODOS)
	private String tagPeriodosDescritpion;

	@Bean
	public Docket eDesignApi(SwaggerConfigProperties swaggerConfigProperties) {
		return new Docket(DocumentationType.SWAGGER_2).groupName(swaggerConfigProperties.getTitle())
				.apiInfo(apiInfo(swaggerConfigProperties)).enable(Boolean.valueOf(swaggerConfigProperties.getEnabled()))
				.select().apis(RequestHandlerSelectors.basePackage("cl.previred.desafio.uno.nivel3.api.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(Boolean.valueOf(swaggerConfigProperties.getUseDefaultResponseMessages()))
				.enableUrlTemplating(Boolean.valueOf(swaggerConfigProperties.getEnableUrlTemplating()))
				.tags(new Tag(tagPeriodos, tagPeriodosDescritpion));

	}

	@Bean
	public UiConfiguration uiConfig(SwaggerConfigProperties swaggerConfigProperties) {
		return UiConfigurationBuilder.builder().deepLinking(Boolean.valueOf(swaggerConfigProperties.getDeepLinking()))
				.displayOperationId(Boolean.valueOf(swaggerConfigProperties.getDisplayOperationId()))
				.defaultModelsExpandDepth(Integer.valueOf(swaggerConfigProperties.getDefaultModelsExpandDepth()))
				.defaultModelExpandDepth(Integer.valueOf(swaggerConfigProperties.getDefaultModelExpandDepth()))
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(Boolean.valueOf(swaggerConfigProperties.getDisplayRequestDuration()))
				.docExpansion(DocExpansion.NONE).filter(Boolean.valueOf(swaggerConfigProperties.getFilter()))
				.maxDisplayedTags(Integer.valueOf(swaggerConfigProperties.getMaxDisplayedTags()))
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(Boolean.valueOf(swaggerConfigProperties.getShowExtensions()))
				.tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}

	private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {
		return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle())
				.description(swaggerConfigProperties.getDescription()).version(swaggerConfigProperties.getApiVersion())
				.build();
	}
}
