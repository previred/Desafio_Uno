package com.previred.periodos.ws;

import com.previred.periodos.swagger.codegen.model.Periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeriodosService {

	private static final Logger log = LoggerFactory.getLogger(PeriodosService.class);
    

    public Periodo getPeriodosFaltantes() {
    	RestTemplate restTemplate = new RestTemplate();
    	Periodo periodos = restTemplate.getForObject(
				"http://127.0.0.1:8080/periodos/api", Periodo.class);
    	LocalDate fechaCreacion = periodos.getFechaCreacion();
    	LocalDate fechaFin = periodos.getFechaFin();    	
    	Set<LocalDate> fechasFaltantes = new HashSet();
    	for (LocalDate d = fechaCreacion; !d.isAfter(fechaFin); d = d.plusMonths(1)) {
    	      if (!periodos.getFechas().contains(d))
    	      {
    	    	  fechasFaltantes.add(d);
    	      }
    	}
    	periodos.setFechasFaltantes(fechasFaltantes.stream()
                .sorted()
                .collect(Collectors.toList()));		
        return periodos;
    }
    
    
}
