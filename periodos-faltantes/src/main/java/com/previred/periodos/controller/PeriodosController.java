package com.previred.periodos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.periodos.error.ResourceNotFoundException;
import com.previred.periodos.model.Periodo;
import com.previred.periodos.service.IGeneraDatosService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping(path = "${server.path.base}")
public class PeriodosController {

	@Autowired
	private IGeneraDatosService generaDatos;

	@GetMapping(path = "${server.path.gdd.get}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class),
			@ApiResponse(code = 400, message = "Error en Request"),
			@ApiResponse(code = 401, message = "No Autorizado"),
			@ApiResponse(code = 404, message = "Error Interno del Servidor") })
	public ResponseEntity<?> obtenerPeriodosGDD() throws ResourceNotFoundException {
		Periodo result;
		result = generaDatos.obtenerGDD();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(path = "${server.path.periodos.post}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Periodo con lista de fechas faltantes", response = Periodo.class),
			@ApiResponse(code = 400, message = "Error en Request"),
			@ApiResponse(code = 401, message = "No Autorizado"),
			@ApiResponse(code = 404, message = "Error Interno del Servidor") })
	public ResponseEntity<?> obtenerPeriodosFaltantes(@RequestBody Periodo payload) {
		Periodo result;
		result = generaDatos.obtenerFechasFaltantes(payload);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(path = "${server.path.periodos.get}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Periodo con lista de fechas y fechas faltantes", response = Periodo.class),
			@ApiResponse(code = 400, message = "Error en Request"),
			@ApiResponse(code = 401, message = "No Autorizado"),
			@ApiResponse(code = 404, message = "Error Interno del Servidor") })
	public ResponseEntity<?> obtenerPeriodosGDDconPeriodosFaltantes() {
		Periodo result;
		result = generaDatos.obtenerGDDconFechasFaltantes();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
