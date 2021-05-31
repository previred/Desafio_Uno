package com.previred.periodos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.periodos.exception.PeriodosServiceException;
import com.previred.periodos.model.PeriodoResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiPeriodosClient {

	@Value("${api.periodos.url}")
	private String url;

	/**
	 * Cliente de API Periodos.
	 * 
	 * @return {@link PeriodoResponse}
	 * @throws PeriodosServiceException
	 */
	public PeriodoResponse buscarPeriodos() throws PeriodosServiceException {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PeriodoResponse> response = restTemplate.getForEntity(url, PeriodoResponse.class);
		log.info("Respuesta API {}", response);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			PeriodoResponse res = response.getBody();
			return res;
		}
		throw new PeriodosServiceException("No data found!" + response.getStatusCodeValue());
	}

}
