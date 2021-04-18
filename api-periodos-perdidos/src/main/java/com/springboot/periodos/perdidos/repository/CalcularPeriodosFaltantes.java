package com.springboot.periodos.perdidos.repository;

import com.springboot.periodos.perdidos.model.Periodos;
import com.springboot.periodos.perdidos.model.PeriodosFaltantes;

/**
 * 
 * @author crist
 *
 */

public interface CalcularPeriodosFaltantes {

	public PeriodosFaltantes calcularPeriodos(Periodos periodos);
}
