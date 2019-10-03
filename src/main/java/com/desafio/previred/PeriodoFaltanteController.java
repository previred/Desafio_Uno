package com.desafio.previred;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PeriodoFaltanteController {
	private final AtomicLong counter = new AtomicLong();
	private String urlGDD = "";
	@Autowired
	Environment environment;

	@RequestMapping("/periodofaltante")
	public PeriodoFaltante periodoFaltante() {
		PeriodoFaltante periodoFaltante = new PeriodoFaltante();
		LocalDate fechaIni;

		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();

		RestTemplate restTemplate = new RestTemplate();
		urlGDD = environment.getProperty("gdd.periodos");
		try {
			Periodo periodo = restTemplate.getForObject(urlGDD, Periodo.class);
			periodoFaltante.setFechas(periodo.getFechas());
			periodoFaltante.setFechaCreacion(periodo.getFechaCreacion());
			periodoFaltante.setFechaFin(periodo.getFechaFin());
			periodoFaltante.setId(counter.incrementAndGet());

			for (fechaIni = periodo.getFechaCreacion(); !fechaIni.equals(periodo.getFechaFin()); fechaIni = fechaIni
					.plusMonths(1), fechasFaltantes.add(fechaIni))
				;
			fechasFaltantes.removeAll(periodo.getFechas());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		periodoFaltante.setFechasFaltantes(new ArrayList<LocalDate>());
		periodoFaltante.getFechasFaltantes().addAll(fechasFaltantes);

		return periodoFaltante;
	}
}
