package com.previred.periodos.cliente;

import java.util.Arrays;

import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomRestTemplate extends RestTemplate {

	public static RestTemplate getRestTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/json"));
		converter.setObjectMapper(mapper);
		return new RestTemplate(Arrays.asList(converter));
	}
	
}