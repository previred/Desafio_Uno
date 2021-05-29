package com.desafio.periodosperdidos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
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
public class Swagger {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(ModelAndView.class, View.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.desafio.periodosperdidos.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Periodos Perdidos")
                .description("Devielve los periodos perdidos del proceso")
                .contact(new Contact("Jesus J Alcala P","https://www.systig.com/","systig.alcala@gmail.com"))
                .version("1.0")
                .termsOfServiceUrl("https://www.systig.com/")
                .build();
    }
}
