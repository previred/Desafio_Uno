package com.devru.desafiouno.webservices.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import com.devru.desafiouno.webservices.WebServiceRequest;

public class ObtenerPeriodosRequest implements WebServiceRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4944663464794230919L;

	private String trx;
	
	/**
	 * @return the trx
	 */
	public String getTrx() {
		return trx;
	}

	/**
	 * @param trx the trx to set
	 */
	public void setTrx(String trx) {
		this.trx = trx;
	}

	@Override
	public Map<String, String> getArgumentosWs() {
		Map<String, String> args = new LinkedHashMap<>();
		args.put("trx", trx);
		return args;
	}

}
