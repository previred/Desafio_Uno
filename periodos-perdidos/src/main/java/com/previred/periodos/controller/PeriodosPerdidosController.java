package com.previred.periodos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.periodos.exception.PeriodosServiceException;
import com.previred.periodos.model.PeriodoResponse;
import com.previred.periodos.service.PeriodosPerdidosService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PeriodosPerdidosController {

	@Autowired
	private PeriodosPerdidosService periodosPerdidosService;

	@GetMapping(path = "/")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Periodos Perdidos is running");
	}

	@GetMapping(path = "/perdidos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> periodosFaltantes() {

		PeriodoResponse res;
		try {
			res = periodosPerdidosService.buscarPeriodos();
			return ResponseEntity.ok(res);
		} catch (PeriodosServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

}
