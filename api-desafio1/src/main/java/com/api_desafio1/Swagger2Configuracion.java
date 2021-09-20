package com.api_desafio1;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan(basePackages = {"com.api_desafio1.controller"})
@EnableSwagger2
public class Swagger2Configuracion {

	@Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .apis(RequestHandlerSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }
 
    private ApiInfo usersApiInfo() {
	   return new ApiInfoBuilder().title("API PREVIRED").description("").version("1.0.0").build();
    }
	
	@Bean
	public RestTemplate restTemplate() {
		
		RestTemplate objTemplate = new RestTemplate();
		
		objTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
		
		return objTemplate;
	}
}
