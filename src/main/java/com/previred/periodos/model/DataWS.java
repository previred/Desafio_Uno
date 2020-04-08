package com.previred.periodos.model;

import java.io.Serializable;

/**
*
* @author arojas
*/
public class DataWS implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean success;
	private int code;
	private String message;
	private Object data;

	public DataWS() {
		super();
	}

	public DataWS(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Respuesta [success=" + success + ", code=" + code + ", message=" + message + ", data=" + (data!=null) + "]";
	}
}
