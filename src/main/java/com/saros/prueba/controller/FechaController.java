package com.saros.prueba.controller;

import java.time.LocalDate;

import com.saros.prueba.dto.RespuestaGdd;
import com.saros.prueba.dto.RespuestaDto;
import com.saros.prueba.service.fechaService;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author saros
 *
 */
@RestController
public class FechaController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private fechaService utilitarioFecha;
	@Value("${urlservicio}")
	private String url;
	
	private final AtomicLong counter = new AtomicLong ();
	
	@GetMapping("/getfechas")
	public ResponseEntity<Object> getFechas()
	{
		RespuestaDto respuesta =  new RespuestaDto ();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ResponseEntity<RespuestaGdd> result ;
		
		HttpHeaders headers = new HttpHeaders ();
		HttpEntity<String> entity = new HttpEntity <> ("parameters", headers);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		try {
			result = restTemplate.exchange(url, HttpMethod.GET, entity, RespuestaGdd.class);
			if (result.getStatusCode().value() == 200)
			{
				respuesta.setId(counter.incrementAndGet());
				respuesta.setFechaCreacion(result.getBody().getFechaCreacion());
				respuesta.setFechaFin(result.getBody().getFechaFin());
				respuesta.setFechas(result.getBody().getFechas());
				respuesta.setFechasFaltantes(utilitarioFecha.getFechasFaltantes(LocalDate.parse (result.getBody().getFechaCreacion(), formatter),
			    		LocalDate.parse(result.getBody().getFechaFin()), result.getBody().getFechas()));
				return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
			}
			else 
			{
				return new ResponseEntity<Object>(null, result.getStatusCode());
			}

		}
		catch (Exception e)
		{   
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
