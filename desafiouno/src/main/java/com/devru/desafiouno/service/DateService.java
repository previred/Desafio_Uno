package com.devru.desafiouno.service;

import com.devru.desafiouno.dto.Periodo;
import com.devru.desafiouno.webservices.dto.ObtenerPeriodosResponse;

/**
* interface DateService para logica de negocio de los Periodos
*
* @author Devru
* @version 17-04-21
*/
public interface DateService {

	/**
	 * 
	 * Calcula periodos faltantes 
	 * @param Recibe objeto de tipo ObtenerPeriodosResponse del servicio externo para los periodos iniciales
	 * @return Devuelve objeto Periodo con listado periodos iniciales y periodos faltantes calculados
	 */
	Periodo calcularPeriodos(ObtenerPeriodosResponse response);
	
}
