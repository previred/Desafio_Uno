package com.hossman.periodos.swagger.codegen.api;

import com.hossman.periodos.swagger.codegen.exception.ApiException;

public class NotFoundException extends ApiException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
