package cl.cconcha.desafio1.service;

import cl.cconcha.desafio1.domain.Periodo;

public interface PeriodoService {
	/**
	 * Consume un servicio rest y completa los periodos faltantes.
	 * @return El objeto {@link Periodo} completado
	 */
	public Periodo fillPeriodos();
}
