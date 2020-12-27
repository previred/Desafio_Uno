package com.devru.desafiouno.webservices;

import com.devru.desafiouno.webservices.dto.ObtenerPeriodosRequest;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

/*
 * Clase para implementar las llamdas a los servicios web externos
 * */

public class WebServiceFacadeImpl implements WebServiceFacade {

	private WebService externalWebServicePeriodos;
	
	/**
	 * @return the externalWebServicePeriodos
	 */
	public WebService getExternalWebServicePeriodos() {
		return externalWebServicePeriodos;
	}

	/**
	 * @param externalWebServicePeriodos the externalWebServicePeriodos to set
	 */
	public void setExternalWebServicePeriodos(WebService externalWebServicePeriodos) {
		this.externalWebServicePeriodos = externalWebServicePeriodos;
	}

	@Override
	public ObtenerPeriodosResponse obtenerPeriodos(ObtenerPeriodosRequest serviceRequest) {
		return (ObtenerPeriodosResponse) externalWebServicePeriodos.call(serviceRequest);
	}

}
