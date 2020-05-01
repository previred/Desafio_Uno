package com.previred.dataaggregator.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.previred.dataaggregator.client.PeriodosClient;
import com.previred.dataaggregator.client.http.service.GDDService;

@Configuration
public class HttpClientConfig {
	
	
	
	@Bean
	public PeriodosClient periodosClient(RestTemplate rest, 
			@Value ("${com.previred.api}")  final String previredApi)	{
			
			return new GDDService(rest,previredApi);
	}
}
