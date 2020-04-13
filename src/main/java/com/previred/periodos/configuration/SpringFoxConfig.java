package com.previred.periodos.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("${swagger.titulo}")
    private String titulo;

    @Value("${swagger.descripcion}")
    private String descripcion;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.terminos-servicio}")
    private String terminosServicio;

    @Value("${swagger.contacto.nombre}")
    private String contactoNombre;

    @Value("${swagger.contacto.url}")
    private String contactoUrl;

    @Value("${swagger.contacto.email}")
    private String contactoEmail;

    @Value("${swagger.licencia.nombre}")
    private String licenciaNombre;

    @Value("${swagger.licencia.url}")
    private String licenciaUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.previred.periodos.controller"))
                .paths(PathSelectors.ant("/*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                titulo,
                descripcion,
                version,
                terminosServicio,
                new Contact(contactoNombre, contactoUrl, contactoEmail),
                licenciaNombre, licenciaUrl, Collections.emptyList());
    }
}
