package com.previred.periodos.misolucion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.previred.periodos.misolucion.comandos.ProcesadorDeComandos;

@SpringBootApplication
public class MiSolucionApplication implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MiSolucionApplication.class);
	private static final String COMMAND_LINE = "mi-solucion";
	private final ProcesadorDeComandos procesadorDeComandos;
	
	public MiSolucionApplication(ProcesadorDeComandos procesadorDeComandos) {
		this.procesadorDeComandos = procesadorDeComandos;
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(MiSolucionApplication.class, args);
		
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(args.containsOption(COMMAND_LINE)) {
			LOGGER.info("Es command line");
			procesadorDeComandos.process(args);
		}
	}

}
