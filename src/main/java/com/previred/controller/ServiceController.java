package com.previred.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.business.ServiceBusiness;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value="cabecera", description="Servicio que consume API CDD entregada por previred")
public class ServiceController {
	
	@ApiOperation(value="Retorna Listado de Fechas")
	@GetMapping(value = "/fechas", produces = "application/json")
	private String findById(){
		ServiceBusiness response = new ServiceBusiness();
		return response.init();
	}
	
	
}
