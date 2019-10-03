package cl.fsoto1.config;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class Swagger2Config {	
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("cl.fsoto1.controlador"))
	      .paths(PathSelectors.any())
	      .build()
	      .apiInfo(apiInfo())
	      .alternateTypeRules( AlternateTypeRules.newRule(
                  typeResolver.resolve(List.class, LocalDate.class),
                  typeResolver.resolve(List.class, Date.class), Ordered.HIGHEST_PRECEDENCE))
	      ;
	}
	 
	@Autowired
	private TypeResolver typeResolver;
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
      	      "GDD Consumer", 
      	      "API para completar periodos", 
      	      "V1", 
      	      null, 
      	      new Contact("Franco Soto", null, "f.soto@outlook.com"), 
      	    null, null, Collections.emptyList());
	}
}
