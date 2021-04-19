package com.github.maraya.fechasfaltantes.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author marcelo.araya-gomez
 *
 */
@Configuration
@EnableSwagger2
public class FechasFaltantesConfiguration {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
        	.select()                                  
        	.apis(RequestHandlerSelectors.any())
        	.paths(PathSelectors.any())
        	.build();                                           
    }
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
