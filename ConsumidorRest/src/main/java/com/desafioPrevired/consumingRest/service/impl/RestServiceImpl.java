package com.desafioPrevired.consumingRest.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafioPrevired.consumingRest.model.PeriodoIn;
import com.desafioPrevired.consumingRest.model.PeriodoOut;
import com.desafioPrevired.consumingRest.service.RestService;

@Service("restService")
public class RestServiceImpl implements RestService {

	private static final Log LOG = LogFactory.getLog(RestServiceImpl.class);

	@Override
	public PeriodoIn obtenerPeriodo(String url) {
		RestTemplate restTemplate = new RestTemplate();
		LOG.info("Obteniendo JSON con periodo desde servicio");
		PeriodoIn periodoIn = restTemplate.getForObject(url, PeriodoIn.class);

		return periodoIn;
	}

	@Override
	public List<LocalDate> encontrarFechasFaltantes(LocalDate fechaCreacion, LocalDate fechaFin,
			List<LocalDate> fechas) {
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		
		for (LocalDate date = fechaCreacion; date.isBefore(fechaFin); date = date.plusMonths(1))
		{
			fechasFaltantes.add(date);
		}
		
		fechasFaltantes.removeAll(fechas);
		
		return fechasFaltantes;
	}

	@Override
	public PeriodoOut obtenerPeriodosFaltantes(String url) {
		PeriodoOut periodoOut = new PeriodoOut();
		PeriodoIn periodoIn = this.obtenerPeriodo(url);
		LOG.info("Periodo Previred obtenido desde servicio --> " + periodoIn.toString());
		Long id = periodoIn.getId();
		LocalDate fechaCreacion = periodoIn.getFechaCreacion();
		LocalDate fechaFin = periodoIn.getFechaFin();
		List<LocalDate> fechas = periodoIn.getFechas();

		LOG.info("Armando respuesta de salida desde servicio");
		periodoOut.setId(id);
		periodoOut.setFechaCreacion(fechaCreacion);
		periodoOut.setFechaFin(fechaFin);
		periodoOut.setFechas(fechas);
		LOG.info("Obteniendo periodos faltantes desde servicio");
		periodoOut.setFechasFaltantes(encontrarFechasFaltantes(fechaCreacion, fechaFin, fechas));

		return periodoOut;
	}

}
