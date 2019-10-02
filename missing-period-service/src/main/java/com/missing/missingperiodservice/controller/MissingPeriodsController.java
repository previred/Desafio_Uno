package com.missing.missingperiodservice.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriBuilderFactory;

import com.missing.missingperiodservice.model.MissingPeriods;

@RestController
@RequestMapping("/missingperiods")
public class MissingPeriodsController {

	@Autowired
	RestTemplate rest;

	@GetMapping("/api")
	public MissingPeriods getMissingPeriods() {

		MissingPeriods missingperiods = rest.getForObject("http://127.0.0.1:8080/periodos/api", MissingPeriods.class);

		LocalDate fechainicio = missingperiods.getFechaCreacion();
		LocalDate fechafin = missingperiods.getFechaFin();
		LocalDate f = fechainicio;

		while (f.isBefore(fechafin)) {
			f = f.plusMonths(1);
			if (!missingperiods.getFechas().contains(f)) {
				missingperiods.getFechasFaltantes().add(f);
			}
		}

		return missingperiods;

	}

}