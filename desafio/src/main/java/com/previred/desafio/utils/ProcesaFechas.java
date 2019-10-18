package com.previred.desafio.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.previred.desafio.model.Periodo;

public class ProcesaFechas {
	private final Periodo periodo;

	/**
	 * Inicializa el rango de fecha a procesar
	 * 
	 * @param periodo
	 */
	public ProcesaFechas(Periodo periodo) {
		this.periodo = periodo;
	}

	public List<LocalDate> extraeFechasFaltantes() {

		List<LocalDate> fechas = generaListaFecha(periodo.getFechaCreacion(), periodo.getFechaFin());

		fechas.removeAll(periodo.getFechas());
		return fechas;
	}

	private List<LocalDate> generaListaFecha(@Valid LocalDate fechaCreacion, @Valid LocalDate fechaFin) {
		List<LocalDate> listaFechas = new ArrayList<>();
		Date fechaInicio = convertToDateViaSqlDate(fechaCreacion);
		Date fechaFinal = convertToDateViaSqlDate(fechaFin);

		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fechaInicio);
		Calendar fechaMaxima = Calendar.getInstance();
		fechaMaxima.setTime(fechaFinal);

		// Bucle para recorrer el intervalo, en cada paso se le suma un mes.
		while (!nuevaFecha.after(fechaMaxima)) {
			LocalDate siguenteFecha = convertToLocalDateViaMilisecond(nuevaFecha.getTime()).withDayOfMonth(1);
			listaFechas.add(siguenteFecha);
			nuevaFecha.add(Calendar.DAY_OF_MONTH, 31);
		}
		listaFechas.add(fechaFin);
		return listaFechas;
	}

	private Date convertToDateViaSqlDate(LocalDate dateToConvert) {
		return java.sql.Date.valueOf(dateToConvert);
	}

	private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
		return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	@Override
	public String toString() {
		return "ProcesaFechas [periodo=" + periodo + "]";
	}


}
