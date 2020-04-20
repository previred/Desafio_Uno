package com.previred.periodos.configuration;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SpringFoxConfig {
    private final TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .directModelSubstitute(LocalDate.class, String.class)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(SortedSet.class, LocalDate.class),
                                typeResolver.resolve(SortedSet.class, String.class), Ordered.HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.previred.periodos"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Documentacion del Ejercicio Desafío 1: Periodos perdidos",
                "Desafio Tecnico de Previred https://github.com/joseyustiz/Desafio_Uno Nivel 3",
                "API 0.1",
                "Terms of service",
                new Contact("Jose Yustiz", "www.joseyustiz.com", "joseyustiz@gmail.com"),
                "License of API", "API license URL",
                Collections.emptyList());
    }
}
