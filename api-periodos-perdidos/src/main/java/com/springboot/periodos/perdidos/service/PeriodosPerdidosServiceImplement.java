package com.springboot.periodos.perdidos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.periodos.perdidos.client.PeriodosClientRest;
import com.springboot.periodos.perdidos.model.Periodos;

/**
 * 
 * @author crist
 *
 */

@Service("serviceFeign")
public class PeriodosPerdidosServiceImplement implements PeriodosPerdidoService {

	@Autowired
	private PeriodosClientRest clienteFeign;

	/**
	 * 
	 */
	@Override
	public Periodos getPeriodos() {
		return clienteFeign.getPeriodos();
	}

}
