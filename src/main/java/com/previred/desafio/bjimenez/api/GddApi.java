package com.previred.desafio.bjimenez.api;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.previred.desafio.bjimenez.api.model.GddResponseModel;

@Component
public class GddApi {

	private static final Logger log = LoggerFactory.getLogger(GddApi.class);

	@Value("${app.gdduri}")
	private String gdduri;

	public GddResponseModel getPeriodos() {

		log.debug("Uri:" + gdduri);

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<GddResponseModel> responseEntity = restTemplate.exchange(gdduri, HttpMethod.GET, entity,
				GddResponseModel.class);

		GddResponseModel gddResponseModel = responseEntity.getBody();

		// Debug info

		log.debug("fechaCreacion: " + gddResponseModel.getFechaCreacion());
		log.debug("fechaFin: " + gddResponseModel.getFechaFin());

		if (gddResponseModel.getFechas() != null) {
			log.debug("fechas: " + gddResponseModel.getFechas().toString());
		}

		return gddResponseModel;

	}
}
