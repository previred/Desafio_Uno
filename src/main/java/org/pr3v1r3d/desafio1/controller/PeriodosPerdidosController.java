package org.pr3v1r3d.desafio1.controller;

import org.pr3v1r3d.desafio1.model.PeriodosPerdidosResponse;
import org.pr3v1r3d.desafio1.service.PeriodosPerdidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class PeriodosPerdidosController {

	@Autowired
	private PeriodosPerdidosService periodosPerdidosService;

	@Operation(summary = "Obtiene una lista de fechas desde el servicio Generador de Datos y completa las fechas que faltan")
	@GetMapping(value = "/periodos-perdidos", produces = { "application/json" })
	ResponseEntity<PeriodosPerdidosResponse> periodosPerdidos() throws Exception {
		return new ResponseEntity<PeriodosPerdidosResponse>(periodosPerdidosService.periodosPerdidos(), HttpStatus.OK);
	}
}
