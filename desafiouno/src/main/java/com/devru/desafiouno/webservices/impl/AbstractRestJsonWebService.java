package com.devru.desafiouno.webservices.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractRestJsonWebService extends AbstractWebService {

	private final RestTemplate restTemplate;
	
	public AbstractRestJsonWebService(String webServiceUrl) {
		super(webServiceUrl);
		this.restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		messageConverters.add(new FormHttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
	}

	/**
	 * @return the restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	public HttpHeaders getHttpHeadersAccepJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}

}
