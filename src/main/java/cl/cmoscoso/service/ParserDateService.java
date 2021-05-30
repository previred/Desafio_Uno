package cl.cmoscoso.service;

import java.net.URISyntaxException;

import org.springframework.web.client.RestClientException;

import cl.cmoscoso.dto.DatesParsedDTO;

public interface ParserDateService {
	public DatesParsedDTO getParseDates() throws RestClientException, URISyntaxException;
}
