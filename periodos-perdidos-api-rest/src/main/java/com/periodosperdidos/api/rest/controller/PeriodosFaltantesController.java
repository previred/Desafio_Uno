package com.periodosperdidos.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.periodosperdidos.api.rest.service.IPeriodosFaltantesService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "Controller Periodos Faltantes")
public class PeriodosFaltantesController {

	@Autowired
	private IPeriodosFaltantesService periodosFaltantesService;

	@GetMapping("/periodos/faltantes")
	public Object getPeriodosFaltantes() {

		return periodosFaltantesService.getPeriodosFaltantes();

	}

	@GetMapping("/periodos/api-gdd")
	public String getPeriodosApiGDD() {

		return periodosFaltantesService.getPeriodosApiGDD();

	}

}
