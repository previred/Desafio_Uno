package com.aleatay.previred.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 *
 * Creo una clase abstracta por si luego necesitamos implementar peticiones Rest de otro tipo
 * Por ejemplo: Post, PUT, etc
 * */
@PropertySource("classpath:apiPeriodos.properties")
public abstract class MainRestClient {

    @Value("${api.header.key}")
    private String headerKey;

    @Value("${api.header.value}")
    private String headerValue;

    // Voy a dejar por defecto la implementacion de tipo GET para efectos del desafio
    protected final String sendJsonRequest(String request) {

        String body;
        ResponseEntity<String> response;

        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerKey, headerValue);

        response = restTemplate.exchange(request, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        body = response.getBody();

		return body;
    }
}
