package cl.hossman.facade.exceptions;


/* Se manejan excpetions a nivel de capa */

public class ApiException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	
    public ApiException (String code, String msg) {
        super(msg);
        this.setCode(code);
    }
   
	public ApiException (String code,String msg, Throwable t) {
		super(msg,t);
		this.setCode(code);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
