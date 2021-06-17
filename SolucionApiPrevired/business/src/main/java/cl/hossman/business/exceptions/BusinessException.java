package cl.hossman.business.exceptions;

public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;

	public BusinessException (String msg) {
		super(msg);
	}
	public BusinessException (String code, String msg, Throwable t) {
		super(msg,t);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}


