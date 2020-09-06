package cl.cconcha.desafio1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.cconcha.desafio1.domain.Periodo;
import cl.cconcha.desafio1.service.PeriodoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Api", tags = {"periodos faltantes"})
@RequestMapping(value = "periodos")
@RestController
public class PeriodoController {

	@Autowired
	private PeriodoService periodoService;
	
	@ApiOperation(value = "endpoint que muestra las fechas faltantes", response = Periodo.class)
	@RequestMapping(value = "/faltantes", method = RequestMethod.GET)
	public ResponseEntity<Periodo> obtenerPeriodosFaltantes(){
		return new ResponseEntity<Periodo>(periodoService.fillPeriodos(), HttpStatus.OK);
	}
}
