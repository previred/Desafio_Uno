package cl.previred.ms.periodos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringfoxConfig {

    @Value("${swagger.ui.enabled: false}") private boolean isEnabled;
    @Value("${swagger.info.name: Nombre de la API}") private String apiName;
    @Value("${swagger.info.description: Descripción de la API}") private String apiDescription;
    @Value("${swagger.info.version: Versión de la API}") private String apiVersion;
    @Value("${swagger.info.contact.name: Nombre del líder técnico}") private String contactName;
    @Value("${swagger.info.contact.mail: contacto@email.com}") private String contactEmail;

    /**
     * Basic Swagger configuration
     * @return {@link Docket} Swagger configuration
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .enable(isEnabled)
                .useDefaultResponseMessages(false)
                ;
    }

    /**
     * Sets the API information for Swagger UI
     * @return {@link ApiInfo} API Information
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                apiName,
                apiDescription,
                apiVersion,
                null,
                new Contact(contactName,null,contactEmail),
                null,
                null,
                Collections.emptyList()
        );
    }

    /**
     * Configures the Swagger UI:
     *    - Activates/Deactivates the "TRY IT OUT" button for Swagger UI
     * @return {@link UiConfiguration} Swagger UI Configuration
     */
    @Bean
    public UiConfiguration uiConfig() {
        /*
         - Available methods are: "get", "head", "post", "put", "delete", "connect", "options", "trace", "patch"
         - Leave blank if you want to disable the 'TRY IT OUT' button for all methods
         */
        final String[] methodsWithTryItOutButton = {  };
        return UiConfigurationBuilder.builder().supportedSubmitMethods(methodsWithTryItOutButton).build();
    }

}