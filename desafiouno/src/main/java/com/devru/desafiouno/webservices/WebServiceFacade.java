package com.devru.desafiouno.webservices;

import com.devru.desafiouno.webservices.dto.ObtenerPeriodosRequest;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

public interface WebServiceFacade {

	/*firma del metodo que obtiene los periodos desde el Generador Datos Desafio */
	ObtenerPeriodosResponse obtenerPeriodos(ObtenerPeriodosRequest serviceRequest);
}
