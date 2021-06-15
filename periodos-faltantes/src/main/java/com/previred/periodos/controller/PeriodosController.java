package com.previred.periodos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.periodos.error.ApiError;
import com.previred.periodos.model.Periodo;
import com.previred.periodos.service.IGeneraDatosService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "${server.path.base}")
public class PeriodosController {

	@Autowired
	private IGeneraDatosService generaDatos;
	    
    @GetMapping(path = "${server.path.gdd.get}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class),
            @ApiResponse(code = 400, message = "Error en Request", response = ApiError.class),
			@ApiResponse(code = 401, message = "No Autorizado", response = ApiError.class),
			@ApiResponse(code = 502, message = "Error Interno del Servidor", response = ApiError.class) })
    public ResponseEntity<?> obtenerPeriodosGDD() {
        Periodo result;
		try {
			result = generaDatos.obtenerGDD();
		} catch (Exception e) {
			ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(error, error.getStatus());
		}
    	
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping(path = "${server.path.periodos.post}", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class),
            @ApiResponse(code = 400, message = "Error en Request", response = ApiError.class),
			@ApiResponse(code = 401, message = "No Autorizado", response = ApiError.class),
			@ApiResponse(code = 502, message = "Error Interno del Servidor", response = ApiError.class) })
    public ResponseEntity<?> obtenerPeriodosFaltantes(@RequestBody Periodo payload) {
        Periodo result;
		try {
			result = generaDatos.obtenerFechasFaltantes(payload);
		} catch (Exception e) {
			ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(error, error.getStatus());
		}
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(path = "${server.path.periodos.get}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class),
            @ApiResponse(code = 400, message = "Error en Request", response = ApiError.class),
			@ApiResponse(code = 401, message = "No Autorizado", response = ApiError.class),
			@ApiResponse(code = 502, message = "Error Interno del Servidor", response = ApiError.class) })
    public ResponseEntity<?> obtenerPeriodosGDDconPeriodosFaltantes() {
    	Periodo result;
		try {
			result = generaDatos.obtenerGDDconFechasFaltantes();
		} catch (Exception e) {
			ApiError error = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
			return new ResponseEntity<>(error, error.getStatus());
		}
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
