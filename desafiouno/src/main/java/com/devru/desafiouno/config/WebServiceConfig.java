package com.devru.desafiouno.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import com.devru.desafiouno.webservices.WebServiceFacade;
import com.devru.desafiouno.webservices.WebServiceFacadeImpl;
import com.devru.desafiouno.webservices.impl.ObtenerPeriodosWebServiceExternal;

/*
 * Clase para configurar las llamadas a servicios externos
 * 
 * */

@Configuration
public class WebServiceConfig {

	@Value("${external.service.url.periodo}")
	private String externalServicePeriodo;
	
	@Bean
	@Scope(value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public WebServiceFacade	webServiceFacade() {
		WebServiceFacadeImpl facadeImpl = new WebServiceFacadeImpl();
		
		facadeImpl.setExternalWebServicePeriodos(new ObtenerPeriodosWebServiceExternal(externalServicePeriodo));
		
		return facadeImpl;
	}
}
