package com.previred.gdd.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.previred.gdd.config.GeneradorDatosException;
import com.previred.gdd.model.Periodo;

@Service
public class GeneradorDatosService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private static final String URI_PERIODO_API = "http://localhost:8080/periodos/api";
	
	public Periodo getPeriodos() {
		Periodo periodos = getPeriodosApi();
		if(periodos == null)
			throw new GeneradorDatosException("No pude generar fechas.");
		periodos.setFechasFaltantes(obtenerFechasFaltantes(periodos));
		return periodos;
	}
	
	Periodo getPeriodosApi(){
		RestTemplate restTemplate = new RestTemplate();
		try{
			Periodo response = restTemplate.getForObject(URI_PERIODO_API, Periodo.class);
			log.info("llamada a la api con exitosa");
			return response;
		}catch(RestClientException rce) {
			log.error(rce.getMessage());
			throw new GeneradorDatosException("periodos/api No esta Funcionando.");
		}
	}
	
	/**
	 * Metodo que obtiene todos los meses segun fecha inicio y fin
	 * y elimina las ya existentes en Periodo.fechas
	 * @param periodo
	 * @return
	 */
	List<LocalDate> obtenerFechasFaltantes(Periodo periodo){
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		LocalDate fechaCreacion = periodo.getFechaCreacion();
		do {
			fechaCreacion = fechaCreacion.plusMonths(1).withDayOfMonth(1);
			fechasFaltantes.add(fechaCreacion);
		}
		while(fechaCreacion.isBefore(periodo.getFechaFin()));
		if(!periodo.getFechas().isEmpty())
			fechasFaltantes.removeAll(periodo.getFechas());
		return fechasFaltantes;
	}
}
