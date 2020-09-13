package com.example.desafio_uno.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket periodosPerdidosApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.desafio_uno.controller"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Alan Zúñiga", "www.github.com/alanzleon","alan.zleon@gmail.com");

        return new ApiInfoBuilder().title("Periodos Perdidos")
                .description("Calculo de periodos perdidos en base a información consumida al generador de datos.")
                .version("1.0")
                .contact(contact)
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
