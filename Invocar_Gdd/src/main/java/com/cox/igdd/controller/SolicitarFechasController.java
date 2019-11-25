package com.cox.igdd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cox.igdd.models.OutListadoFechas;
import com.cox.igdd.services.SolicitarFechasService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Muestra las fechas faltantes.", description = "Este servicio muestra las fechas faltantes de un listado obtenido al consumir el servicio GDD.")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class SolicitarFechasController {

	@Autowired
	private SolicitarFechasService solicitarFechasService;

	@RequestMapping(value = "/invocar", method = RequestMethod.GET)
	@ApiOperation(value = "Invoca listado de fechas.", notes = "Solicita listado de fechas al servicio GDD.")
	public OutListadoFechas invocar() {

		return solicitarFechasService.invocar();

	}

}
