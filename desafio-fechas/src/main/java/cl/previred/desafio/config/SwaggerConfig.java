package cl.previred.desafio.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig{
	

	@Value("${swagger.ui.enabled: false}") private boolean isEnabled;
    @Value("${swagger.info.name: Nombre de la API}") private String apiName;
    @Value("${swagger.info.description: Descripción de la API}") private String apiDescription;
    @Value("${swagger.info.version: Versión de la API}") private String apiVersion;
    @Value("${swagger.info.contact.name: Nombre del líder técnico}") private String contactName;
    @Value("${swagger.info.contact.mail: contacto@email.com}") private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("(/manage.*|/error|/health/.*)")))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(apiName)
                .description(apiDescription)
                .version(apiVersion)
                .build();
    }
   
   
}