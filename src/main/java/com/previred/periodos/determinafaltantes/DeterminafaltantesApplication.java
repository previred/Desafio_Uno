package com.previred.periodos.determinafaltantes;

import com.previred.periodos.configuration.AppConfig;
import com.previred.periodos.configuration.DeterminaFaltantesProperties;
import com.previred.periodos.configuration.LocalDateConverter;
import com.previred.periodos.configuration.SpringFoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({AppConfig.class, DeterminaFaltantesProperties.class, LocalDateConverter.class, SpringFoxConfig.class} )
@SpringBootApplication
public class DeterminafaltantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeterminafaltantesApplication.class, args);
	}

}
