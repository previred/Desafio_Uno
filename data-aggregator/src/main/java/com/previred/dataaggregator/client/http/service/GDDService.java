package com.previred.dataaggregator.client.http.service;

import org.springframework.web.client.RestTemplate;

import com.previred.dataaggregator.client.PeriodosClient;
import com.previred.dataaggregator.service.Periodo;

import java.net.URI;


public class GDDService implements PeriodosClient {

	private final RestTemplate restTemplate;
	
	private final String previredApi;

	public GDDService(RestTemplate rest, String previredApi) {
		this.restTemplate = rest;
		this.previredApi = previredApi;
	}

	public Periodo getPeriods() {
		URI uri = URI.create(this.previredApi);
		return this.restTemplate.getForObject(uri, Periodo.class);
	}



}