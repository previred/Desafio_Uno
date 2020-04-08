package com.previred.periodos.backend.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.periodos.backend.service.PeriodoService;
import com.previred.periodos.model.Periodo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
*
* @author arojas
*/
@RestController
public class PeriodoController {

	final static Logger logger = Logger.getLogger(PeriodoController.class);

	@Autowired
	PeriodoService periodoService;
	
	@ApiOperation(value = "Lista de periodos faltantes a procesar", nickname = "periodos-faltantes", notes = "", response = Periodo.class, tags={ "periodos-faltantes" })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Periodo y lista de fechas faltantes", response = Periodo.class),
    	@ApiResponse(code = 500, message = "Ha ocurrido un problema, se genero una Exception")
    })
    @RequestMapping(value = "/api",
        produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, 
        method = RequestMethod.GET)
	public ResponseEntity<?> getData() {
		try {
			Periodo data = periodoService.getPeriodosFaltantes();
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
