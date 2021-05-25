package cl.previred.services.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.entity.IncompletePeriod;
import cl.previred.rest.response.CompletePeriodResponse;
import cl.previred.services.GDDCompleteService;
import cl.previred.services.GDDService;
import cl.previred.utils.AppUtils;

@Service
public class GDDCompleteServiceImpl implements GDDCompleteService {

	private static final Logger logger = LogManager.getLogger(GDDCompleteServiceImpl.class);

	@Autowired
	GDDService gddService;

	public CompletePeriodResponse getCompletePeriod() throws IOException, ParseException {

		logger.info("[getCompletePeriod] Start");

		List<Date> datesMissing = new ArrayList<>();

		//Se obtienen el periodo+fechas incompletas del servicio GDD
		IncompletePeriod incompletePeriod = gddService.getIncompletePeriod();

		//Se crea una lista copleta con todas las fechas del rango de la lista anterior de GDD
		List<Date> fechasCompletas = AppUtils.createFullList(incompletePeriod.getFechaCreacion(),
				incompletePeriod.getFechaFin());

		//Se obtienen las fechas incompletas del servicio GDD
		List<Date> fechasIncompletas = incompletePeriod.getFechas();

		for (Date fecha : fechasCompletas) {
			// El método contains () funciona más rápido en HashSet para estos casos
			boolean contains = fechasIncompletas.contains(fecha);
			if (!contains) {
				datesMissing.add(fecha);
			}
		}

		// Se ordena lista creada
		Collections.sort(datesMissing, (c1, c2) -> {
			if (AppUtils.convertToLocalDateViaInstant(c1).isBefore(AppUtils.convertToLocalDateViaInstant(c2)))
				return -1;
			else
				return 1;
		});

		CompletePeriodResponse periodoCompletadoResponse = new CompletePeriodResponse();
		periodoCompletadoResponse.setId(incompletePeriod.getId());
		periodoCompletadoResponse.setFechaCreacion(incompletePeriod.getFechaCreacion());
		periodoCompletadoResponse.setFechaFin(incompletePeriod.getFechaFin());
		periodoCompletadoResponse.setFechas(incompletePeriod.getFechas());
		periodoCompletadoResponse.setFechasFaltantes(datesMissing);

		logger.info("[getCompletePeriod] End");


		return periodoCompletadoResponse;
	}

}