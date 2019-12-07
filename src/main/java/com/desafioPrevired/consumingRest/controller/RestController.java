package com.desafioPrevired.consumingRest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.desafioPrevired.consumingRest.ConsumingRestApplication;
import com.desafioPrevired.consumingRest.model.PeriodoIn;
import com.desafioPrevired.consumingRest.model.PeriodoOut;
import com.desafioPrevired.consumingRest.service.RestService;

@Controller
@RequestMapping("/rest") 
public class RestController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumingRestApplication.class);
	private static final String JSON_URL = "http://localhost:8080/periodos/api"; 
	
	@Autowired
	@Qualifier("restService")
	private RestService restService;
	
	@GetMapping("/probarRequest")
	public ResponseEntity<PeriodoIn> verificarJsonRecibido() {
		LOG.info("Obtenemos periodos desde controller...");
		PeriodoIn periodoIn = restService.obtenerPeriodo(JSON_URL);
		LOG.info("Periodos obtenidos desde controller --> " + periodoIn.toString());
		
		return new ResponseEntity<PeriodoIn>(periodoIn, HttpStatus.OK);	
	}
	
	@GetMapping("/periodosFaltantes")
	public ResponseEntity<PeriodoOut> obtenerPeriodosFaltantes() {
		LOG.info("Obtenemos periodos faltantes desde controller...");
		PeriodoOut periodoOut = restService.obtenerPeriodosFaltantes(JSON_URL);
		LOG.info("Periodos faltantes obtenidos desde controller --> " + periodoOut.toString());
		
		return new ResponseEntity<PeriodoOut>(periodoOut, HttpStatus.OK);	
	}
	
}
