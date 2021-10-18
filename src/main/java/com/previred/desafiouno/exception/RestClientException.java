package com.previred.desafiouno.exception;

public class RestClientException extends Exception{

	private static final long serialVersionUID = 202553311081272028L;

	public RestClientException(String message) {
		super(message);
	}
	public RestClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
