package com.previred.desafioUno.exception;

import org.springframework.http.HttpStatus;

public class DesafioException extends Exception {

	private static final long serialVersionUID = 1626562681461089268L;
	
	private HttpStatus status;
	
	public DesafioException(String msg, Throwable e, HttpStatus status) {
		super(msg, e);
		this.status = status;
	}
	
	public DesafioException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}
	
	public DesafioException(Throwable e, HttpStatus status) {
		super(e);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
