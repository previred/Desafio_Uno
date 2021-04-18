package com.springboot.periodos.perdidos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.periodos.perdidos.exception.ServiceException;
import com.springboot.periodos.perdidos.model.Periodos;
import com.springboot.periodos.perdidos.model.PeriodosFaltantes;
import com.springboot.periodos.perdidos.repository.CalcularPeriodosFaltantes;
import com.springboot.periodos.perdidos.service.PeriodosPerdidoService;

/**
 * 
 * @author crist
 *
 */
@CrossOrigin
@RestController
public class ApiPeriodosPerdidosController {

	@Autowired
	@Qualifier("serviceFeign")
	private PeriodosPerdidoService service;

	@Autowired
	CalcularPeriodosFaltantes faltantes;

	/**
	 * endpoint que retorna las fechas faltantes de la api GDD
	 * 
	 * @return
	 */
	@GetMapping("/periodos/perdidos")
	public ResponseEntity<PeriodosFaltantes> getPeriodosFaltantes() throws ServiceException{
			Periodos periodos = service.getPeriodos();
			PeriodosFaltantes pf = faltantes.calcularPeriodos(periodos);
			ResponseEntity<PeriodosFaltantes> respuesta = new ResponseEntity<>(pf, HttpStatus.OK);
			return respuesta;
	}
}
