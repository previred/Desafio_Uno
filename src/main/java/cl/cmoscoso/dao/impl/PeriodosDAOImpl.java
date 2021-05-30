package cl.cmoscoso.dao.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.cmoscoso.config.exceptions.PeriodosServiceException;
import cl.cmoscoso.dao.PeriodosDAO;
import cl.cmoscoso.entity.PeriodosEntity;

@Component
public class PeriodosDAOImpl implements PeriodosDAO {
	@Value("${cl.cmoscoso.periodosUrl}")
	private String serviceUrl;

	@Autowired
	private RestTemplate rest;

	@Override
	public PeriodosEntity getPeriodos() throws PeriodosServiceException {
		URI uri = null;
		try {
			uri = new URI(serviceUrl);
		} catch (Exception e) {
			throw new PeriodosServiceException(e.getMessage());
		}

		ResponseEntity<PeriodosEntity> response = rest.getForEntity(uri, PeriodosEntity.class);

		return response.getBody();
	}

}
