package com.desafio.previred.apidesafio.fechasfaltantes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.desafio.previred.apidesafio.generador.FechasFaltantesRequest;

@Component
public class FechasFaltantesService {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public FechasFaltantesResponse buscaFechasFaltantes(FechasFaltantesRequest request) {
		List<LocalDate> fechasFaltantes = new ArrayList<>();
		try {
			fechasFaltantes = fechasFaltantes(request.getFechaCreacion(), request.getFechaFin(),
					request.getFechas());
			
		} catch (Exception e) {
			log.error("Ha ocurrido un error al buscar fechsa faltantes :{}",e.getMessage());
		}
		return new FechasFaltantesResponse(request.getId(), request.getFechaCreacion(), request.getFechaFin(),
				request.getFechas(), fechasFaltantes);
	}

	private List<LocalDate> fechasFaltantes(LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
		List<LocalDate> fechasFaltantes = new ArrayList<>();
		fechaCreacion = fechaCreacion.plusMonths(1);
		for (LocalDate date = fechaCreacion; date.isBefore(fechaFin); date = date.plusMonths(1)) {
			if (!fechas.contains(date)) {
				fechasFaltantes.add(date);
			}
		}
		return fechasFaltantes;
	}
}
