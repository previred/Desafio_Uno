package com.luisnunez.springboot.api.periodos.faltantes.models.services;

import com.luisnunez.springboot.api.periodos.faltantes.models.Periodos;
import com.luisnunez.springboot.api.periodos.faltantes.models.PeriodosFaltantes;

public interface PeriodoFaltanteService {
	
	public PeriodosFaltantes traePeriodosFaltantes();
	
	public Periodos traePeriodosGDD();

}
