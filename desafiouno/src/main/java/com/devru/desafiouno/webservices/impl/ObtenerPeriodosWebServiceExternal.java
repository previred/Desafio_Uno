package com.devru.desafiouno.webservices.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.devru.desafiouno.webservices.WebServiceRequest;
import com.devru.desafiouno.webservices.WebServiceResponse;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosRequest;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

public class ObtenerPeriodosWebServiceExternal extends AbstractRestJsonWebService {

	public ObtenerPeriodosWebServiceExternal(String webServiceUrl) {
		super(webServiceUrl);
	}

	@Override
	public WebServiceResponse call(WebServiceRequest serviceRequest) {
		if(Boolean.FALSE.equals(serviceRequest instanceof ObtenerPeriodosRequest)) {
			throw new IllegalStateException("WebServiceRequest no es del tipo esperado");
		}
		UriComponentsBuilder builder = instanceUriComponentsBuilder(serviceRequest);
		HttpEntity<?> entity = new HttpEntity<>(getHttpHeadersAccepJson());
		HttpEntity<ObtenerPeriodosResponse> responseService = getRestTemplate().exchange(
				builder.build().encode().toUri(), 
				HttpMethod.GET,
				entity,
				ObtenerPeriodosResponse.class);
		
		return responseService.getBody();
	}

}
