package org.pr3v1r3d.desafio1.controller;

import org.pr3v1r3d.desafio1.model.FechasFaltantesResponse;
import org.pr3v1r3d.desafio1.service.FechasFaltantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class FechasFaltantesController {

	@Autowired
	private FechasFaltantesService fechasFaltantesService;

	@Operation(summary = "Obtiene una lista de fechas desde el servicio Generador de Datos y completa las fechas que faltan")
	@GetMapping(value = "/periodos-perdidos", produces = { "application/json" })
	ResponseEntity<FechasFaltantesResponse> fechasFaltantes() throws Exception {
		return new ResponseEntity<FechasFaltantesResponse>(fechasFaltantesService.fechasFaltantes(), HttpStatus.OK);
	}
}
