package com.springboot.periodos.perdidos.response;

public class Response {

	private Object data;
	private int status;
	private String message;
	
	/**
	 * 
	 */
	public Response() {
		super();
	}

	/**
	 * @param data
	 * @param status
	 * @param message
	 */
	public Response(Object data, int status, String message) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
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

}
