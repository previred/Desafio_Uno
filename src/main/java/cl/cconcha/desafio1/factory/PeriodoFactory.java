package cl.cconcha.desafio1.factory;

import java.time.LocalDate;
import java.util.ArrayList;

import cl.cconcha.desafio1.domain.Periodo;

public class PeriodoFactory {

	/**
	 * Construye la instancia de Periodo
	 * @param periodoOriginal Periodo original que se va a copiar
	 * @return instancia de periodo
	 */
	public static Periodo getInstance(Periodo periodoOriginal) {
		Periodo periodo = new Periodo();
		periodo.setId(periodoOriginal.getId());
		periodo.setFechaCreacion(periodoOriginal.getFechaCreacion());
		periodo.setFechaFin(periodoOriginal.getFechaFin());
		periodo.setFechas(periodoOriginal.getFechas());
		periodo.setFechasFaltantes(new ArrayList<LocalDate>());
		return periodo;
	}
}
