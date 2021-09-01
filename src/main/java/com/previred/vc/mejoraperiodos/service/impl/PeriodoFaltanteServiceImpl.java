package com.previred.vc.mejoraperiodos.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.vc.mejoraperiodos.model.Periodo;
import com.previred.vc.mejoraperiodos.model.PeriodoFaltante;
import com.previred.vc.mejoraperiodos.service.PeriodoFaltanteService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PeriodoFaltanteServiceImpl implements PeriodoFaltanteService {

	@Value("${endpoint.service.gdd}")
	private String urlPrevired;
	
	@Override
	public PeriodoFaltante getPeriodosFaltantes(Periodo periodo) {
		List<LocalDate> allDates = getDateForPeriod(periodo.getFechaCreacion(), periodo.getFechaFin());
		log.info("fecha Inicio: "+periodo.getFechaCreacion()+ " - fecha fin: " + periodo.getFechaFin());
		log.info("cantidad de periodos totales: "+allDates.size());
		List<LocalDate> result = allDates.stream()
				  .distinct()
				  .filter(f -> !periodo.getFechas().contains(f))
				  .collect(Collectors.toList());
		log.info("cantidad de periodos retornados por servicio gdd: "+(periodo.getFechas().size()));
		log.info("cantidad de periodos faltantes para completar el total: "+(result.size()));
		PeriodoFaltante faltante = new PeriodoFaltante(periodo);
		faltante.setFechasFaltantes(result);
		return faltante;
	}

	private List<LocalDate> getDateForPeriod(LocalDate dateInit, LocalDate dateEnd) {
		List<LocalDate> alls = new ArrayList<LocalDate>();
		while (dateInit.isBefore(dateEnd)) {
			alls.add(dateInit);
			dateInit = dateInit.plusMonths(1);
		}
		alls.add(dateEnd);
		return alls;
	} 
	
	@Override
	public Periodo callAPIPrevired() {
		log.info("llamando al servicio gdd de previred");
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);

	    long t1 = System.currentTimeMillis();
        ResponseEntity<Periodo> periodoRS = restTemplate.exchange(urlPrevired, HttpMethod.GET, entity, Periodo.class);
		log.info("time: "+(System.currentTimeMillis()-t1)+ " milisegundos");
        return periodoRS.getBody();
	}
		
}
