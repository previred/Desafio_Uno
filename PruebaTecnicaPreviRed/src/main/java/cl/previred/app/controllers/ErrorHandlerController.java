package cl.previred.app.controllers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cl.previred.app.data.dto.ErrorDto;
import cl.previred.app.errores.ErrorConexcionApiException;
import cl.previred.app.errores.ErrorGlobalSistema;

@RestControllerAdvice
public class ErrorHandlerController {
	
	@ExceptionHandler(ErrorGlobalSistema.class)
	public ResponseEntity<?> errorGlobalSistema(Exception ex) {
		ErrorDto error = new ErrorDto("ErrorGlobalSistema", new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
	@ExceptionHandler(ErrorConexcionApiException.class)
	public ResponseEntity<?> errorConexcionApiException(ErrorConexcionApiException ex) {
		ErrorDto error = new ErrorDto("ErrorConexcionApiException", new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
