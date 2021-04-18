package com.springboot.periodos.perdidos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.periodos.perdidos.exception.ServiceException;
import com.springboot.periodos.perdidos.response.Response;

@ControllerAdvice(annotations=RestController.class)
public class ErrorHandler {
	
	@ExceptionHandler(ServiceException.class)
	private ResponseEntity<Response> errorHandler(ServiceException genericException) {
		Response err = new Response();
		err.setStatus(genericException .getStatus());
		err.setMessage(genericException.getMessage());
		return new ResponseEntity<Response>(err, HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Response> errorHandler(Exception a ) {
		if (a instanceof ServiceException) { 
			ServiceException e = (ServiceException) a; 
			Response err = new Response();
			err.setStatus(e .getStatus());
			err.setMessage(e.getMessage());
			return new ResponseEntity<Response>(err, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}else {
			Response err = new Response();
			err.setStatus(500);
			err.setMessage("Error, Ha Ocurrido un error interno");
			return new ResponseEntity<Response>(err, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}
	
}