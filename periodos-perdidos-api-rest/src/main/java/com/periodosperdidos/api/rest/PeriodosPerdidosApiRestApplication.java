package com.periodosperdidos.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PeriodosPerdidosApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeriodosPerdidosApiRestApplication.class, args);
	}

	@RestController
	class HelloController {
		@GetMapping("/")
		String hello() {
			return "Hello from App Engine !";
		}
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.periodosperdidos.api"))
				.paths(PathSelectors.ant("/api/*")).build();
	}

}
