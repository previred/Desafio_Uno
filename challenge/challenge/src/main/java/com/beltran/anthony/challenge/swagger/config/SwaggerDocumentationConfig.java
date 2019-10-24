package com.beltran.anthony.challenge.swagger.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerDocumentationConfig {
    @Autowired
    private TypeResolver typeResolver;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API challenge")
                .description("Challenge")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", "beltrananthony92@gmail.com"))
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(List.class, LocalDate.class),
                        typeResolver.resolve(List.class, java.sql.Date.class), Ordered.HIGHEST_PRECEDENCE))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.beltran.anthony.challenge.api"))
                .build()
                .genericModelSubstitutes(ResponseEntity.class)
                .apiInfo(apiInfo());
    }

}
