package com.github.maraya.fechasfaltantes.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.maraya.fechasfaltantes.model.Periodo;

import lombok.extern.log4j.Log4j2;

/**
 * 
 * @author marcelo.araya-gomez
 *
 */
@RestController
@RequestMapping(path = "/periodos")
@Log4j2
public class FechasFaltantesController {
	
	public static final String PERIODOS_ENDPOINT = "http://localhost:8081/periodos/api";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(path = "/fechas-faltantes", produces = { "application/json" })
	public Periodo getFechasFaltantes() {
		
		log.info("Consultando servicio de fechas faltantes");
		ResponseEntity<Periodo> response = restTemplate.getForEntity(PERIODOS_ENDPOINT, Periodo.class);
		Periodo periodo = response.getBody();
		
		if (response.getStatusCode() == HttpStatus.OK) {
			LocalDate fechaCreacion = periodo.getFechaCreacion();
			LocalDate fechaFin = periodo.getFechaFin();
			List<LocalDate> fechasFaltantes = new ArrayList<>();
			
			do {
				fechaCreacion = fechaCreacion.plusMonths(1);
				
				if (!periodo.getFechas().contains(fechaCreacion)) {
					fechasFaltantes.add(fechaCreacion);
				}
			} while (!fechaCreacion.equals(fechaFin));
			
			periodo.setFechasFaltantes(fechasFaltantes);
			return periodo;
		}
		
		return null;
	}
}
