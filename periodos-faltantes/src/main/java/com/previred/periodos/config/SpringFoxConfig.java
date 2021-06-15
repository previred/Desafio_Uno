package com.previred.periodos.config;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.fasterxml.classmate.TypeResolver;
import com.previred.periodos.utils.Constantes;

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
public class SpringFoxConfig {
    
	
	private ApiInfo apiInfo() {
        return new ApiInfo(
                Constantes.CONSTANTE_TITULO,
                Constantes.CONSTANTE_DESCRIPCION,
                Constantes.CONSTANTE_VERSION,
                Constantes.CONSTANTE_TERMINOS,
                new Contact(Constantes.CONSTANTE_CONTACTO_NOMBRE, Constantes.CONSTANTE_CONTACTO_URL, Constantes.CONSTANTE_CONTACTO_EMAIL),
                Constantes.CONSTANTE_LICENCIA_NOMBRE,
                Constantes.CONSTANTE_LICENCIA_URL,
                Collections.emptyList());
    }
	
	@Bean
    public Docket api() {
		TypeResolver typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
        		.alternateTypeRules(AlternateTypeRules.newRule( 						
						typeResolver.resolve(List.class, LocalDate.class),  
						typeResolver.resolve(List.class, String.class), Ordered.HIGHEST_PRECEDENCE))
        		.apiInfo(apiInfo())
        		.select()
        		.apis(RequestHandlerSelectors.basePackage(Constantes.CONSTANTE_PACKAGE_APIS))
                .paths(PathSelectors.any())
                .build();
    }

}
