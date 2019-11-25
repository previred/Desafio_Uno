package com.previred.periodosperdidos.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.previred.periodosperdidos.servicio.model.Periodo;
import com.previred.periodosperdidos.servicio.model.PeriodosPerdidos;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que se conecta al GDD y determina los Periodos Perdidos
 * 
 * @autor: Jorge San Martin
 */
@Service
@Slf4j
public class PeriodosPerdidosService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${generadorDeDatos.url}")
	private String urlGDD;
	
	/**
	 * Invoca al Generador de Datos obteniendo los periodos, y luego determina los Periodos Perdidos.
	 * 
	 * @return PeriodosPerdidos
	 * @throws Exception 
	 */
	public PeriodosPerdidos obtenerPeriodos() throws Exception {
		Periodo periodo = null;
		
		log.info("Invocando a Generador de Datos en la direccion: " + urlGDD + " ...");
		try {
			periodo = restTemplate.getForObject(urlGDD, Periodo.class);
			log.info("Datos obtenidos exitosamente");
		}
		catch(RestClientException ex) {
			log.error("Error al conectarse con servicio Generador de Datos", ex);
			
			throw ex;
		}
		
		Map<LocalDate, LocalDate> fechasObtenidas = new HashMap<LocalDate, LocalDate>();
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		
		if (periodo == null) {
			log.error("El Generador de Datos no entrego datos.");			
			throw new Exception("El Generador de Datos no entrego datos");			
		} 
		
		
		if (periodo.getFechaCreacion() == null && periodo.getFechaFin() == null) {
			log.error("No es posible determinar los Periodos Faltantes sin la Fecha de Creacion y Fecha Fin");			
			throw new Exception("No es posible determinar los Periodos Faltantes sin la Fecha de Creacion y Fecha Fin");	
							
		}	
							
		log.info("Determinando los Periodos Perdidos...");
		if (periodo.getFechas() != null && !periodo.getFechas().isEmpty()) {
			fechasObtenidas = periodo.getFechas().stream().collect(Collectors.toMap(fecha -> fecha, fecha -> fecha));
		}
			
		procesarPeriodos(fechasFaltantes, fechasObtenidas, periodo);
		
		return PeriodosPerdidos.builder()
			.fechaCreacion(periodo.getFechaCreacion())
			.fechaFin(periodo.getFechaFin())
			.fechas(periodo.getFechas())
			.fechasFaltantes(fechasFaltantes)
			.id(periodo.getId())
			.build();	

	}
	
	/**
	 * Procesa los periodos
	 * 
	 * @param fechasFaltantes
	 * @param fechasObtenidas
	 * @param periodo
	 */
	private void procesarPeriodos(List<LocalDate> fechasFaltantes, Map<LocalDate, LocalDate> fechasObtenidas, Periodo periodo) {
		LocalDate fechaTemp = periodo.getFechaCreacion();
		if (fechasObtenidas.isEmpty()) {
			while(fechaTemp.isBefore(periodo.getFechaFin()) || fechaTemp.isEqual(periodo.getFechaFin())) {				
				fechasFaltantes.add(fechaTemp);				
				fechaTemp = fechaTemp.plusMonths(1);
			}
		}
		else {
			while(fechaTemp.isBefore(periodo.getFechaFin()) || fechaTemp.isEqual(periodo.getFechaFin())) {
				if (!fechasObtenidas.containsKey(fechaTemp)) {
					fechasFaltantes.add(fechaTemp);
				}
				fechaTemp = fechaTemp.plusMonths(1);
			}
		}	
	}
}
