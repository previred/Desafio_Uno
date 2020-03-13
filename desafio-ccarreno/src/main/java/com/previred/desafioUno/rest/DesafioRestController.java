package com.previred.desafioUno.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafioUno.exception.DesafioException;
import com.previred.desafioUno.rest.client.domain.PeriodosResponse;
import com.previred.desafioUno.service.DesafioService;

@RestController
public class DesafioRestController implements DesafioRest {
	
	@Autowired 
	private DesafioService desafioService;

	@GetMapping(value = "/", produces = { "application/json" })
	public ResponseEntity<PeriodosResponse> desafio() throws DesafioException {
		
		return ResponseEntity.ok(desafioService.executeRandomDateGenerator());
	}
}
