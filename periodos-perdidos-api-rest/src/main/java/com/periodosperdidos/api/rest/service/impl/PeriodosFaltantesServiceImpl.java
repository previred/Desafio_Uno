package com.periodosperdidos.api.rest.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.periodosperdidos.api.rest.domain.Periodo;
import com.periodosperdidos.api.rest.service.IPeriodosFaltantesService;

@Service
public class PeriodosFaltantesServiceImpl implements IPeriodosFaltantesService {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String uri = "http://127.0.0.1:8080/periodos/api";

	@Override
	// Se obtienen las fechas desde la API GDD
	public String getPeriodosApiGDD() {
		String resultado = restTemplate.getForObject(uri, String.class);
		return resultado;
	}

	@Override
	public Periodo getPeriodosFaltantes() {

		Periodo fechasApiGDD = getFechasRamdomApi();

		List<String> fechasFaltantes = new ArrayList<>();

		String fechaInicio = fechasApiGDD.getFechaCreacion();
		String fechaFin = fechasApiGDD.getFechaFin();

		LocalDate fIniLocalDate = LocalDate.parse(fechaInicio);
		LocalDate fFinLocalDate = LocalDate.parse(fechaFin);

		// Se obtienen los primeros dias de cada mes, segun el rango de fechas inicio y
		// fin
		// generado en API GDD
		List<LocalDate> fechasRango = getFechasEntreRangos(fIniLocalDate, fFinLocalDate);

		for (int i = 0; i < fechasRango.size(); i++) {

			if (!buscaFechaApiGDD(fechasApiGDD, fechasRango.get(i).toString())) {
				fechasFaltantes.add(fechasRango.get(i).toString());
			}
		}
		fechasApiGDD.setFechasFaltantes(fechasFaltantes);

		return fechasApiGDD;

	}

	// Retorna true si encuentra la fecha en API GDD
	public boolean buscaFechaApiGDD(Periodo fechasApiGDD, String fecha) {

		for (int i = 0; i < fechasApiGDD.getFechas().size(); i++) {

			if (fechasApiGDD.getFechas().get(i).toString().equals(fecha)) {
				return true;
			}
		}

		return false;

	}

	// Obtiene los días según dos rangos de fechas
	public List<LocalDate> getFechasEntreRangos(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.MONTHS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusMonths(i))
				.collect(Collectors.toList());

	}

	// Obtiene las fechas de la API GDD
	@Override
	public Periodo getFechasRamdomApi() {

		Gson gson = new Gson();

		String fechasRamdom = getPeriodosApiGDD();
		Periodo periodo = gson.fromJson(fechasRamdom, Periodo.class);

		return periodo;
	}

}
