package com.devru.desafiouno.rest;

import java.net.ConnectException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
import com.devru.desafiouno.utils.DesafioUtils;
import com.devru.desafiouno.webservices.WebServiceFacade;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosRequest;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * Servicios Rest a publicar
 * */

@RestController
@RequestMapping("/periodos")
public class PeriodosController {

	private static final Logger log = LoggerFactory.getLogger(PeriodosController.class);
	
	@Autowired
	private WebServiceFacade ws;
	
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
				return ResponseEntity.ok(calcularPeriodos(serviceResponse));
			else
				return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	/*Logica negocio, calcula el resto de los periodos */
	private Periodo calcularPeriodos(ObtenerPeriodosResponse response) {
	
		/*Se prepara el formato de fecha a leer*/
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		/*Se instancian las fechas*/
		YearMonth fechaCreacion = YearMonth.parse(response.getFechaCreacion(), formatter);
		YearMonth fechaFin = YearMonth.parse(response.getFechaFin(), formatter);
		/*Lista de fechas faltantes*/
		List<String> fechasFaltantes = new ArrayList<>();
		
		/*Se valida que la lista de periodos pre-generados por el GDD no sea null*/
		if(response.getFechas() == null || response.getFechas().isEmpty()) {
			response.setFechas(new ArrayList<>());
		}
		while(fechaCreacion.isBefore(fechaFin)) { /*Miestras sea antes de la fecha fin*/
			/*Se incrementa el mes de uno en uno*/
			fechaCreacion = fechaCreacion.plusMonths(1); 
			log.info("fecha calculada: {} ",fechaCreacion+"-01");
			/* Si ya existe en la lista de fechas generadas por GDD (Generador Datos Desafio) servicio externo*/
			if (response.getFechas().contains(fechaCreacion+"-01") || response.getFechaFin().equalsIgnoreCase(fechaCreacion+"-01")) {
				log.info("fecha {} ya existe", fechaCreacion+"-01");
			} else {
				log.info("fecha {} agregada", fechaCreacion+"-01");
				fechasFaltantes.add(fechaCreacion+"-01");
			}
		}
		/*Se mapea el objeto con las dos lista de fechas*/
		Periodo periodo = new Periodo();
		periodo.setFechaCreacion(response.getFechaCreacion());
		periodo.setFechaFin(response.getFechaFin());
		periodo.setFechas(response.getFechas());
		periodo.setFechasFaltantes(fechasFaltantes);
		periodo.setId(response.getId());
		return periodo;
	}
		
}
