package com.luisnunez.springboot.api.periodos.faltantes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisnunez.springboot.api.periodos.faltantes.models.Periodos;
import com.luisnunez.springboot.api.periodos.faltantes.models.PeriodosFaltantes;
import com.luisnunez.springboot.api.periodos.faltantes.models.services.PeriodoFaltanteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Periodos")
public class PeriodoFaltanteController {

	@Autowired
	private PeriodoFaltanteService periodoFaltanteService;

	@ApiOperation(value = "Trae los periodos desde la API GDD")
	@GetMapping("/periodos")
	public Periodos traePeriodos() {
		Periodos periodoResponse = periodoFaltanteService.traePeriodosGDD();
		return periodoResponse;
	}
	
	@ApiOperation(value = "Trae los periodos desde la API GDD mas fechas no incluidas")
	@GetMapping("/periodos-faltantes")
	public PeriodosFaltantes traePeriodosFaltantes() {
		PeriodosFaltantes periodosFaltantes = periodoFaltanteService.traePeriodosFaltantes();
		return periodosFaltantes;
	}

}
