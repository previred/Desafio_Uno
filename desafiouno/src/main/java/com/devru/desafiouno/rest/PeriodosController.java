package com.devru.desafiouno.rest;

import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.devru.desafiouno.dto.Periodo;
import com.devru.desafiouno.service.DateService;
import com.devru.desafiouno.utils.DesafioUtils;
import com.devru.desafiouno.webservices.WebServiceFacade;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosRequest;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
* Controller PeriodosController expose los rest para las acciones sobre los Periodos
*
* @author Devru
* @version 17-04-21
*/
@RestController
@RequestMapping("/periodos")
public class PeriodosController {

	private static final Logger log = LoggerFactory.getLogger(PeriodosController.class);
	
	/* Para consultar servicios externos  */
	@Autowired
	private WebServiceFacade ws;
	
	/* Para consultar servicios de la logica del negocio */
	@Autowired
	private DateService dateService;
	
	@ApiOperation(value = "Completa Periodos", notes = "Completa Periodos entre dos fechas creadas por GDD")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Periodos Calculados correctamente"),
			@ApiResponse(code = 401, message = "Unauthorizede"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 408, message = "Request Timeout"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@RequestMapping(value = "/completar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Periodo> completarPeriodos() {
		log.info("entrando");
		ObtenerPeriodosRequest serviceRequest = new ObtenerPeriodosRequest();
		serviceRequest.setTrx(DesafioUtils.formatDate(DesafioUtils.santiagoDate(), DesafioUtils.FULL_DATE_FORMAT));
		log.info("init ws facade");
		ObtenerPeriodosResponse serviceResponse = null;
		try {
			serviceResponse = ws.obtenerPeriodos(serviceRequest);
		} catch (RestClientException rce) {
			
			if (rce.getCause() instanceof ConnectException) {
				log.error("RestClientException: {}", rce.getMessage());
				return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(null);
			}
			
			if (rce.getCause() instanceof HttpClientErrorException) {
				log.error("HttpClientErrorException: {}", rce.getMessage());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
			
			if (rce.getCause() instanceof HttpServerErrorException) {
				log.error("HttpServerErrorException: {}", rce.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
			
			if (rce.getCause() instanceof UnknownHttpStatusCodeException) {
				log.error("UnknownHttpStatusCodeException: {}", rce.getMessage());
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
			}
					
		} 
		
		if(serviceResponse != null) {
			log.info("response: {}", serviceResponse);
			if(serviceResponse.getFechaCreacion() != null && !serviceResponse.getFechaCreacion().isEmpty() && serviceResponse.getFechaFin() != null && !serviceResponse.getFechaCreacion().isEmpty() )
				return ResponseEntity.ok(dateService.calcularPeriodos(serviceResponse));
			else
				return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.noContent().build();
	}
			
}
