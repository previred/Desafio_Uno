/**
 * 
 */
package com.previred.periodos.misolucion.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.previred.periodos.misolucion.dominio.Periodo;
import com.previred.periodos.misolucion.dominio.PeriodoResultado;
import com.previred.periodos.misolucion.servicios.CalculaPeriodoService;
import com.previred.periodos.misolucion.servicios.ClienteGDDService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Leonardo Silva Bustos
 *
 */
@Api(value = "RestController", produces = "application/json", description = "Api que calcula las fechas faltantes a partir del GDD")
@org.springframework.web.bind.annotation.RestController
public class RestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);
	
	private final CalculaPeriodoService calculaPeriodoService;
	private final ClienteGDDService clienteGDDService;
	
	public RestController(CalculaPeriodoService calculaPeriodoService, ClienteGDDService clienteGDDService) {
		this.calculaPeriodoService = calculaPeriodoService;
		this.clienteGDDService = clienteGDDService;
	}
	
	@GetMapping(path="/mi-solucion")
	@ApiOperation(value = "Consulta servicio GDD y calcula las fechas no contenidas en la resputa", tags = { "Calcula fechas" })
	@ApiResponses(value = { //
			@ApiResponse(code = 200, 
					message = "Respuesta satisfactoria", 
					response = PeriodoResultado.class), 
			@ApiResponse(code =500, 
				message = "Error al consumir el servicio GDD") })
	public ResponseEntity<PeriodoResultado> get() {
		
		Periodo periodo = clienteGDDService.consultaService();
		
		if(periodo == null) {
			LOGGER.error("No se puede consumir el servicio GDD");
			return new ResponseEntity<PeriodoResultado>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		PeriodoResultado resultado = new PeriodoResultado();
		
		resultado.setId(periodo.getId());
		resultado.setFechaCreacion(periodo.getFechaCreacion());
		resultado.setFechaFin(periodo.getFechaFin());
		resultado.setFechas(periodo.getFechas());
		
		List<LocalDate> fechasFaltantes = calculaPeriodoService.fechasFaltantes(periodo);
		
		resultado.setFechasFaltantes(fechasFaltantes);
		
		return new ResponseEntity<PeriodoResultado>(resultado, HttpStatus.OK);
		
	}
	
	
}