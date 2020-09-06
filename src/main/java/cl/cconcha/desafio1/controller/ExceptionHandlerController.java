package cl.cconcha.desafio1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import cl.cconcha.desafio1.domain.PeriodoException;


@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(value = {HttpClientErrorException.class})
	public ResponseEntity<PeriodoException> exceptionHandlerNotFound(HttpClientErrorException ex) {
		PeriodoException exception = new PeriodoException("URL_NOTFOUND", "La url no fue encontrada");
		return new ResponseEntity<PeriodoException>(exception,HttpStatus.OK);
	}
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<PeriodoException> exceptionHandlerNotFound(Exception ex) {
		PeriodoException exception = new PeriodoException("GLOBAL_EX", "Error generico");
		return new ResponseEntity<PeriodoException>(exception,HttpStatus.OK);
	}
}
