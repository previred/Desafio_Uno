package com.previred.prueba.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.previred.prueba.model.FechasFaltantesType;
import com.previred.prueba.service.PeriodosPerdidosService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@ApiResponses(value = { 
		@ApiResponse(code = 429, message = "Too many request")
})

public class PeriodosPerdidosController {
	@Autowired
	PeriodosPerdidosService periodosPerdidosService;
	
	final static Logger logger = LoggerFactory.getLogger(PeriodosPerdidosController.class);
	
	@RequestMapping(value = "/periodos/faltantes/api",method = RequestMethod.GET,produces = "application/json")
	public ResponseEntity<FechasFaltantesType> periodosPerdidos(){
		try {
			return new ResponseEntity<>(periodosPerdidosService.consultarPeriodosPerdidos(), HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.TOO_MANY_REQUESTS);
		}
	}
}
