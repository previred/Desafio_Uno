package com.previred.periodosperdidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.previred.periodosperdidos.swagger.codegen.model.Periodo;

@FeignClient(name="periodos", url = "localhost:8080/periodos")
public interface IPeriodosClient {
	
	@RequestMapping(value = "/api",
	        produces = { "application/json" }, 
	        method = RequestMethod.GET)
	public Periodo periodos();

}
