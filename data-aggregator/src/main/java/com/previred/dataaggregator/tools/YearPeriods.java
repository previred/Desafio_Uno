package com.previred.dataaggregator.tools;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.apache.log4j.Logger;

public class YearPeriods {
	LocalDate MIN;
	LocalDate MAX;
	final Integer MESES = 1;

	final static Logger log = Logger.getLogger(YearPeriods.class);
	
	public List<LocalDate> nextDate(LocalDate date) {
		List<LocalDate> fechas = new ArrayList<LocalDate>();

		while (date.compareTo(this.MAX) != 0) {
			date = date.plusMonths(MESES);
			fechas.add(date);
		} 

		return fechas;
	}

	public List<LocalDate> completePeriods(@Valid List<LocalDate> periodsList, LocalDate fechaCreacion, LocalDate fechaFin) {
		this.MIN = fechaCreacion;
		this.MAX = fechaFin.minusMonths(1);

		List<LocalDate> filterPeriods = this.nextDate(fechaCreacion).stream()
				.filter(s -> !periodsList.stream().anyMatch(mc -> s.equals(mc))).collect(Collectors.toList());

		filterPeriods.stream().forEach(i -> log.info("Filter Periods : " + i));

		return filterPeriods;
	}

}
