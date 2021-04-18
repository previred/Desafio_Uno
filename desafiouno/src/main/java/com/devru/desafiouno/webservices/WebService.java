package com.devru.desafiouno.webservices;

public interface WebService {

	String getWebServiceUrl();
	WebServiceResponse call(WebServiceRequest serviceRequest);
	
}
