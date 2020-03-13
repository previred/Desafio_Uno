package com.previred.desafioUno.service.impl;

import java.time.Month;
import java.time.YearMonth;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.desafioUno.config.ApplicationProperties;
import com.previred.desafioUno.exception.DesafioException;
import com.previred.desafioUno.rest.client.domain.PeriodosResponse;
import com.previred.desafioUno.service.DesafioService;

@Service
public class DesafioServiceImpl implements DesafioService {

	@Autowired
	private ApplicationProperties props;

	@Override
	public PeriodosResponse executeRandomDateGenerator() throws DesafioException {

		PeriodosResponse reponseBody = null;

		try {

			RestTemplate client = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("body", headers);

			String url = String.format("%s://%s:%s%s%s", props.getProtocol(), props.getHost(), props.getPort(),
					props.getContext(), props.getApiPath());
			
			System.out.println(url);

			ResponseEntity<PeriodosResponse> response = client.exchange(url, HttpMethod.GET, entity,
					PeriodosResponse.class);

			if (response.getStatusCode().is2xxSuccessful()) {

				reponseBody = response.getBody();

				YearMonth startDate = YearMonth.of(reponseBody.getFechaCreacion().getYear(),
						Month.of(reponseBody.getFechaCreacion().getMonthValue()));
				YearMonth endDate = YearMonth.of(reponseBody.getFechaFin().getYear(),
						Month.of(reponseBody.getFechaFin().getMonthValue()));

				while (startDate.isBefore(endDate)) {
					startDate = startDate.plusMonths(1);
					reponseBody.addFechaFaltante(startDate.atDay(1));
				}
				Collections.sort(reponseBody.getFechas());

				return reponseBody;
			} else {
				throw new DesafioException("Error al obtener las fechas", response.getStatusCode());
			}
		} catch (Exception e) {
			throw new DesafioException("Error al obtener las fechas", e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
