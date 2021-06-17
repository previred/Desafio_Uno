package cl.hossman.connector.exceptions;

public class ClienteRestException extends Exception {

	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	private static final long serialVersionUID = 1L;

	public ClienteRestException (String code,String msg ) {
		super(msg);
		this.code = code;
	}
	public ClienteRestException (String code, String msg, Throwable t) {
		super(msg,t);
		this.code = code;
	}

}


