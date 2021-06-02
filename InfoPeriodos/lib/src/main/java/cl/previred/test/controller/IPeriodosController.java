package cl.previred.test.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import cl.previred.test.model.SalidaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

public interface IPeriodosController {


	default Optional<NativeWebRequest> getRequest() {
		return Optional.empty();
	}

	@ApiOperation(value = "Obtiene los periodos faltantes en un rango determinado."
			,nickname = "PeriodosController"
			,notes = "Rango determinado es random y de prueba."
			,response = SalidaDTO.class, tags = { "IPeriodosController", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna los datos del cliente consultado", response = SalidaDTO.class),
			@ApiResponse(code = 400, message = "Bad request", response = SalidaDTO.class),
			@ApiResponse(code = 401, message = "Unauthorized. Request was understood but provided credentials info is invalid", response = SalidaDTO.class),
			@ApiResponse(code = 422, message = "Request validation failed", response = SalidaDTO.class),
			@ApiResponse(code = 502, message = "Internal server error", response = SalidaDTO.class) })

	@RequestMapping(value = "/obtener/periodos/faltantes/",
	produces = { "application/json" },
	consumes = { "application/json" },
	method = RequestMethod.POST)
	default ResponseEntity<SalidaDTO> obtenerPeriodosFaltantes(
			@ApiParam(value = "'Numero unico para identificar la transaccion dentro del servicio.'", required = true) 
			@RequestHeader(value = "Trace-Id", required = true) String TraceId)  
			{
				return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
			}
}