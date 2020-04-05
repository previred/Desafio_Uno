/**
 * 
 */
package com.previred.fechas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.previred.fechas.config.Properties;
import com.previred.fechas.request.SalidaFechas;
import com.previred.fechas.util.PostSalida;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Fechas Faltantes ", tags = { "Operacion asociada a la obtención de fechas faltantes" })
public class FechasController {

	@Autowired
	private Properties properties;

	public static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");
	public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");
	public static final String SERVERERROR = "Servidor: {}";
	public static final String CLIENTERROR = "Cliente: {}";

	@ApiOperation(value ="Genera Json con datos de Entrada obtenidos desde Servicio Previred  + Fechas Faltantes ", notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = String.class) })
	@GetMapping(value = "/salida", produces = "aplication/json")

	public ResponseEntity<SalidaFechas> salida() {
		String url = properties.getProperties().get("endpoint");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<SalidaFechas> response = restTemplate.getForEntity(url, SalidaFechas.class);

		PostSalida postSalida = new PostSalida();
		return postSalida.generasalida(response);

	}

}
