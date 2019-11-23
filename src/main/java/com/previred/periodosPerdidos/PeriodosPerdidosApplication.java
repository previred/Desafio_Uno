package com.previred.periodosPerdidos;

import com.previred.periodosPerdidos.Controller.PreviredController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class PeriodosPerdidosApplication implements CommandLineRunner {

	private PreviredController previredController;

	@Autowired
	public PeriodosPerdidosApplication(PreviredController previredController){
		this.previredController = previredController;
	}

	public static void main(String... args) {
		SpringApplication.run(PeriodosPerdidosApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//System.out.println(previredController.periodosPerdidos());
	}

	/**
	 * MetaData para Swagger
	 * @return
	 */
	@Bean
	public Docket swaggerConfiguration(){
		//Retornamos una instancia de Docket preparada
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.previred"))
				.build().apiInfo(ApiDetalle());

	}

	private ApiInfo ApiDetalle(){
		return new ApiInfo(
				"Periodos Perdidos",
				"Desafio Previred","","",
				new springfox.documentation.service.Contact("Matias Arce","","emea.arce@gmail.com"),
				"",
				"",
				Collections.emptyList());
	}
}
