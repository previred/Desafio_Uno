package com.previred.desafio.bjimenez.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MainController {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public List<String> fechasFaltantes(String fechaCreacion, String fechaFin, List<String> fechas) {

		// Convert to localDates objects
		List<LocalDate> ldFechas = new ArrayList<LocalDate>();

		for (String fecha : fechas) {
			LocalDate ldFecha = LocalDate.parse(fecha, formatter);
			ldFechas.add(ldFecha);
		}

		LocalDate ldfechaCreacion = LocalDate.parse(fechaCreacion, formatter);
		LocalDate ldfechaFin = LocalDate.parse(fechaFin, formatter);

		List<LocalDate> ldFechasFaltantes = fechasFaltantes(ldfechaCreacion, ldfechaFin, ldFechas);

		List<String> fechasFaltantes = new ArrayList<String>();
		for (LocalDate fechaFaltante : ldFechasFaltantes) {
			fechasFaltantes.add(fechaFaltante.toString());
		}

		return fechasFaltantes;
	}

	public List<LocalDate> fechasFaltantes(LocalDate ldfechaCreacion, LocalDate ldfechaFin, List<LocalDate> dates) {

		List<LocalDate> fechas = new ArrayList<LocalDate>();

		// if not first day in the date month
		if (ldfechaCreacion.getDayOfMonth() != 1) {
			ldfechaCreacion = ldfechaCreacion.plusMonths(1);
			ldfechaCreacion = ldfechaCreacion.minusDays(ldfechaCreacion.getDayOfMonth() - 1);
			System.out.println("FechaCreacion ajustada: " + ldfechaCreacion);
		}

		while (ldfechaCreacion.isBefore(ldfechaFin)) {
			ldfechaCreacion = ldfechaCreacion.plusMonths(1);
			if (!isDateInList(ldfechaCreacion, dates)) {
				fechas.add(ldfechaCreacion);
			}
		}

		return fechas;
	}

	private boolean isDateInList(LocalDate date, List<LocalDate> dates) {

		for (LocalDate dateItem : dates) {
			if (dateItem.isEqual(date)) {
				return true;
			}
		}

		return false;
	}
}
