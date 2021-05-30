package cl.cmoscoso.dao;

import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;

import cl.cmoscoso.entity.PeriodosEntity;

public interface PeriodosDAO {

	public PeriodosEntity getPeriodos() throws RestClientException, URISyntaxException;

}
