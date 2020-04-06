package com.previred.vc.mejoraperiodos.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	@Value("${endpoint.service.previred}")
	private String urlPrevired;
	
	@Override
	public PeriodoFaltante getPeriodosFaltantes(Periodo periodo) {
		List<LocalDate> allDates = getDateForPeriod(periodo.getFechaCreacion(), periodo.getFechaFin());
		allDates.removeAll(periodo.getFechas());
		PeriodoFaltante faltante = new PeriodoFaltante(periodo);
		faltante.setFechasFaltantes(allDates);
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
	    RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	    HttpEntity<HttpHeaders> entity = new HttpEntity<HttpHeaders>(headers);

        ResponseEntity<Periodo> periodoRS = restTemplate.exchange(urlPrevired, HttpMethod.GET, entity, Periodo.class);
        return periodoRS.getBody();
	}
	
//	
//	public static void main(String... args) {
//		LocalDate init = LocalDate.of(2014, Month.JANUARY, 1);
//		LocalDate end = LocalDate.of(2017, Month.AUGUST, 1);
//		List<LocalDate> list = getDateForPeriod(init, end);
//		System.out.println(list);
//	}
//	
	
}
