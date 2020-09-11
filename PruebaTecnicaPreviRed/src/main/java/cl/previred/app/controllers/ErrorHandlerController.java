package cl.previred.app.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.previred.app.errores.ErrorConexcionApiException;
import cl.previred.app.errores.ErrorGlobalSistema;

@RestControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(ErrorGlobalSistema.class)
	public ResponseEntity<?> errorGlobalSistema(Exception ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "ErrorGlobalSistema");
		response.put("hora", new Date());
		response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("mensaje", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

	}
	
	@ExceptionHandler(ErrorConexcionApiException.class)
	public ResponseEntity<?> errorConexcionApiException(ErrorConexcionApiException ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("error", "ErrorConexcionApiException");
		response.put("hora", new Date());
		response.put("status", HttpStatus.NOT_FOUND.value());
		response.put("mensaje", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

}
