package com.previred.fechas.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

import com.previred.fechas.request.SalidaFechas;

public class PostSalida {

	public ResponseEntity<SalidaFechas> generasalida(ResponseEntity<SalidaFechas> fecha) {

		LocalDate fechaInicio = fecha.getBody().getFechaCreacion();
		LocalDate fechaFin = fecha.getBody().getFechaFin();
		fechaFin = fechaFin.plusMonths(1);

		LocalDate fechaMovil = fechaInicio;
		List<LocalDate> faltantes = new ArrayList<>();

		int index = 0;
		while (fechaMovil.isBefore(fechaFin)) {
			if (!fecha.getBody().getFechas().contains(fechaMovil)) {
				faltantes.add(index, fechaMovil);
				index++;
			} 
			fechaMovil = fechaMovil.plusMonths(1);
		}

		fecha.getBody().setFechasFaltantes(faltantes);

		return fecha;

	}

}
