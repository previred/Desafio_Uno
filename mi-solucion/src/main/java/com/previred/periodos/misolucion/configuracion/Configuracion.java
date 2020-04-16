/**
 * 
 */
package com.previred.periodos.misolucion.configuracion;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
/**
 * @author Leonardo Silva Bustos
 *
 */
@Configuration
public class Configuracion {

	@Value("${gdd-service.timeout}")
	private int timeout;
	
	@Bean
	public ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		return mapper;
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		try {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
					.loadTrustMaterial(null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
			HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
			httpRequestFactory.setHttpClient(httpClient);

			httpRequestFactory.setConnectionRequestTimeout(this.timeout);
			httpRequestFactory.setConnectTimeout(this.timeout);
			httpRequestFactory.setReadTimeout(this.timeout);

			RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
			
			restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
			return restTemplate;
		} catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
			throw new BeanInitializationException("Can't create Rest Template", e);
		}
	}
	
}
