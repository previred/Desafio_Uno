package cl.cmoscoso.dto;

import java.io.Serializable;

public class ErrorMessageDTO implements Serializable {
	private static final long serialVersionUID = 4742038466298340227L;
	private int statusCode;
	private String timestamp;
	private String message;

	public ErrorMessageDTO() {
	}

	public ErrorMessageDTO(int statusCode, String timestamp, String message) {
		super();
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	 
}
