package com.previred.periodos.servicio;

import java.time.LocalDate;

import com.previred.periodos.model.Periodo;
import com.previred.periodos.model.PeriodoFechasFaltantes;
import com.previred.periodos.util.MyException;

/**
 *
 * @author Nelson Villamizar
 */

public class PeriodosService {

	public PeriodoFechasFaltantes getFechasFaltantes(Periodo periodo) throws MyException {

		PeriodoFechasFaltantes periodoff = new PeriodoFechasFaltantes();

		LocalDate fechaCreacion = periodo.getFechaCreacion();
		LocalDate fechaFin = periodo.getFechaFin();
		periodoff.setFechaCreacion(fechaCreacion);
		periodoff.setFechaFin(fechaFin);
		periodoff.setId(periodo.getId());
		if (fechaFin.compareTo(fechaCreacion) == 0) {
			throw new MyException("Error. fechaCreacion y fechaFin son iguales ");

		}

		if (fechaCreacion.compareTo(fechaFin) > 0) {
			throw new MyException("Error fechaCreacion es mayor que la fechaFin");

		}

		LocalDate fecha = fechaCreacion;
		while (fecha.compareTo(fechaFin) <= 0) {
			if (periodo.getFechas().indexOf(fecha) != -1) {

			} else {
				periodoff.addFechasItem(fecha);
			}

			fecha = fecha.plusMonths(1);

		}

		return periodoff;

	}
}
