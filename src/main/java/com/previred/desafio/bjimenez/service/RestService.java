package com.previred.desafio.bjimenez.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.desafio.bjimenez.api.GddApi;
import com.previred.desafio.bjimenez.api.model.GddResponseModel;
import com.previred.desafio.bjimenez.controller.MainController;
import com.previred.desafio.bjimenez.rest.model.DesafioResponseModel;

@RestController
@RequestMapping("/api")
public class RestService {

	private static final Logger log = LoggerFactory.getLogger(RestService.class);

	@Autowired
	private GddApi gddApi;

	@Autowired
	private MainController mainController;

	@RequestMapping("/fechasfaltantes")
	public DesafioResponseModel fechasFaltantes() {

		log.debug("Get periodos");
		GddResponseModel gddResponseModel = gddApi.getPeriodos();

		String fechaCreacion = gddResponseModel.getFechaCreacion();
		String fechaFin = gddResponseModel.getFechaFin();
		List<String> fechas = gddResponseModel.getFechas();

		log.debug("Calc fechasFaltantes");
		List<String> fechasFaltantes = mainController.fechasFaltantes(fechaCreacion, fechaFin, fechas);

		log.debug("Prepare response");
		DesafioResponseModel desafioResponseModel = new DesafioResponseModel();
		desafioResponseModel.setFechaCreacion(fechaCreacion);
		desafioResponseModel.setFechaFin(fechaFin);
		desafioResponseModel.setFechas(fechas);
		desafioResponseModel.setFechasFaltantes(fechasFaltantes);

		return desafioResponseModel;
	}

}
