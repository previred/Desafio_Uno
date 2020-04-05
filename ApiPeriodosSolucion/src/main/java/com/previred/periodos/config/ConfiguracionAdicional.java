package com.previred.periodos.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
@Component
@Service
public class ConfiguracionAdicional {

	/**
     * Injection de ConfiguracionAdicional que lee la propiedad rest.server.url definida en
     * applicaction.propeties
     */
    
	@Value("${gdd.server.url}")
	private String urlserver;

	@Value("5000")
	private int connectTimeout;
	@Value("30000")
	private int readTimeout;

	public String getUrlserver() {
		return urlserver;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}
}

