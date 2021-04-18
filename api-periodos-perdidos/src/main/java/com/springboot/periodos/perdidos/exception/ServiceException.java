package com.springboot.periodos.perdidos.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int status;
    private String message;
	
    /**
	 * 
	 */
	public ServiceException() {}

	/**
	 * @param status
	 * @param message
	 */
	public ServiceException(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}    
}
