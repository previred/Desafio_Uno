package cl.irk.periodos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.irk.periodos.model.Periodo;
import cl.irk.periodos.model.PeriodoFull;
import cl.irk.periodos.util.GenerateOutput;

@RestController
public class PeriodosController {
    Logger logger = LoggerFactory.getLogger(PeriodosController.class);

	public PeriodosController() {
		super();
	}

	@GetMapping("/periodos/full")
	public PeriodoFull getPeriodos(){
		logger.info("getPeriodos");
		RestTemplate plantilla = new RestTemplate();
		Periodo per = plantilla.getForObject("http://localhost:8080/periodos/api", Periodo.class);
		
		return GenerateOutput.obtieneFaltantes(per);
	}
	
}
