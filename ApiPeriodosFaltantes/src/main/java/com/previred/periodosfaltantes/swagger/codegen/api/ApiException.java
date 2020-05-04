package com.previred.periodosfaltantes.swagger.codegen.api;


public class ApiException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 172064323635015735L;
	private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.setCode(code);
    }
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
