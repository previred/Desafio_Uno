package com.previred.periodosperdidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodosperdidos.exception.GeneracionArchivoException;
import com.previred.periodosperdidos.servicio.GeneradorArchivo;
import com.previred.periodosperdidos.servicio.PeriodosPerdidosService;
import com.previred.periodosperdidos.servicio.model.PeriodosPerdidos;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AppConfig {
	
	@Autowired
    private ConfigurableApplicationContext context;
	
	@Autowired
	private GeneradorArchivo generadorArchivo;

	@Bean
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public GeneradorArchivo generadorArchivo() {
		return new GeneradorArchivo();
	}
	
	@Bean
	public CommandLineRunner run(PeriodosPerdidosService cliente) throws Exception {
		return args -> {
			
			log.info("Iniciando aplicacion de obtencion de Periodos Perdidos...");

			try {
				PeriodosPerdidos periodosPerdidos = cliente.obtenerPeriodos();
								
				generadorArchivo.generar(periodosPerdidos);
				log.info("Aplicacion de obtencion de Periodos Perdidos finalizada con exito");
				SpringApplication.exit(context);
				
			}
			catch (Exception e) {
				if (e instanceof GeneracionArchivoException) {
					log.error("Archivo no generado. Por favor, asegurece de que el directorio cuenta con los privilegios necesarios");					
				}
				log.info("Aplicacion de obtencion de Periodos Perdidos finalizada con error");
				SpringApplication.exit(context);
			}			
		};
	}
	
	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
	    return builder.build();
	    
	}
}
