package com.periodosperdidos.api.rest.service;

import com.periodosperdidos.api.rest.domain.Periodo;

public interface IPeriodosFaltantesService {

	public String getPeriodosApiGDD();

	public Periodo getPeriodosFaltantes();

	public Periodo getFechasRamdomApi();

}
