package io.ajsoft.previred.desafio1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DesafioUnoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioUnoApplication.class, args);
	}
	
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	    		.apiInfo(new ApiInfoBuilder()
	    		        .title("Previred Desafio 1")
	    		        .description("Autor: Alvaro J. Urbaez")
	    		        .version("alpha")
	    		        .build())
	            .select()
	            .apis(RequestHandlerSelectors.basePackage("io.ajsoft.previred"))
	            .paths(PathSelectors.any())
	            .build()
	            .useDefaultResponseMessages(false);
	}

}
