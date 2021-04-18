package com.springboot.periodos.perdidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.periodos.perdidos.model.Periodos;

/**
 * 
 * @author crist
 *
 */

@FeignClient(name="api-periodos", url="localhost:8080")
public interface PeriodosClientRest {

	@RequestMapping(value = "/periodos/api", produces = { "application/json" }, method = RequestMethod.GET)
	public Periodos getPeriodos();
}
