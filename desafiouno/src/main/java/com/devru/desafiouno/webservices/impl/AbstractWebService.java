package com.devru.desafiouno.webservices.impl;

import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import com.devru.desafiouno.webservices.WebService;
import com.devru.desafiouno.webservices.WebServiceRequest;

public abstract class AbstractWebService implements WebService {

	private final String webServiceUrl;
	
	public AbstractWebService(String webServiceUrl) {
		this.webServiceUrl = webServiceUrl;
	}

	@Override
	public String getWebServiceUrl() {
		return this.webServiceUrl;
	}
	
	protected UriComponentsBuilder instanceUriComponentsBuilder(WebServiceRequest serviceRequest) {
		return instanceUriComponentsBuilder(serviceRequest, getWebServiceUrl());
	}
	
	protected UriComponentsBuilder instanceUriComponentsBuilder(WebServiceRequest serviceRequest, String httpUri) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(httpUri);
		if(serviceRequest != null && serviceRequest.getArgumentosWs() != null) {
			for (Map.Entry<String, String> entry : serviceRequest.getArgumentosWs().entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				builder.queryParam(entry.getValue(), entry.getValue());
			}
		}
		return builder;
	}

}
