package com.previred.periodosfaltantes.swagger.codegen.api;


public class NotFoundException extends ApiException {

	private static final long serialVersionUID = 2298577461501186217L;
	private int code;
    public NotFoundException (int code, String msg) {
        super(code, msg);
        this.setCode(code);
    }
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
