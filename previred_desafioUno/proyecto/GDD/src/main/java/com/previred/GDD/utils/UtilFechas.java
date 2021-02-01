package com.previred.GDD.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UtilFechas {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public LocalDate stringToLocalDate(String fecha) throws ParseException {

		Date date = this.dateFormat.parse(fecha);
		LocalDate fechaConvertida = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return fechaConvertida;
	}

	public int diferenciaMeses(String fechaInicio, String fechaFinal) throws ParseException {
		Calendar inicio = new GregorianCalendar();
		Calendar fin = new GregorianCalendar();
		inicio.setTime(this.dateFormat.parse(fechaInicio));
		fin.setTime(this.dateFormat.parse(fechaFinal));
		int years = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
		int meses = years * 12 + (fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH));
		return meses + 1;
	}

	public List<Date> rangoFechas(String fechaInicio, int meses) throws ParseException {
		List<Date> fechas = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.dateFormat.parse(fechaInicio));
		fechas.add(this.dateFormat.parse(fechaInicio));
		for (int i = 0; i < meses - 1; i++) {
			calendar.add(Calendar.MONTH, 1);
			fechas.add(calendar.getTime());
		}
		return fechas;
	}

	public List<Date> fechasAleatotias(List<Date> rangoFechas, int cantidadFechas) {

		List<Date> fechasAleatorias = new ArrayList<Date>();
		int aux = 0;

		do {
			for (int i = 0; i < rangoFechas.size(); i++) {

				if (i == (int) (Math.random() * (rangoFechas.size() - 1) + 1)) {
					if (aux < cantidadFechas) {
						fechasAleatorias.add(rangoFechas.get(i));
						rangoFechas.remove(i);
					}
					aux++;

				}
			}
		} while (aux < cantidadFechas);

		Collections.sort(fechasAleatorias);
		return fechasAleatorias;
	}

	public List<Date> fechasFaltantes(List<Date> rangoFechas, List<Date> fechasAleatorias) {

		List<Date> fechasRestantes = new ArrayList<Date>();

		for (int i = 0; i < rangoFechas.size(); i++) {
			for (int j = 0; j < fechasAleatorias.size(); j++) {
				if (!rangoFechas.get(i).equals(fechasAleatorias.get(j))) {
					fechasRestantes.add(rangoFechas.get(i));
					break;
				}
			}
		}
		Collections.sort(fechasRestantes);
		return fechasRestantes;
	}

	public List<String> datesToString(List<Date> fechas) {

		List<String> fechaString = new ArrayList<String>();

		for (int i = 0; i < fechas.size(); i++) {
			fechaString.add(this.dateFormat.format(fechas.get(i)));
		}
		return fechaString;
	}
}
