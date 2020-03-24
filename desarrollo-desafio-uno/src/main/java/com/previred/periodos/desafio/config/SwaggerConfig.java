package com.previred.periodos.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by sati on 24-03-20.
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.previred.periodos.desafio"))
                .build()
                .apiInfo(getMetaInfo());
    }

    private ApiInfo getMetaInfo() {
        return new ApiInfoBuilder()
                .title("Solución desafio uno")
                .description("Servicio que retorna la primer fecha mensual dentro de un rango de fechas.")
                .version("1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .contact(
                        new Contact("Jorge Estaban Satizabal","", "satihoyos@gmail.com")
                ).build();
    }
}
