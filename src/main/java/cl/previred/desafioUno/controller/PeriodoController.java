package cl.previred.desafioUno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

import cl.previred.desafioUno.dto.PeriodoDTO;
import cl.previred.desafioUno.servicio.PeriodoServicio;

@Api(value = "api", description = "Muestra periodo")
@RestController
public class PeriodoController {

	@Autowired
	PeriodoServicio periodoServicio;

	@ApiOperation(value = "Lista de periodos completando fechas faltantes", nickname = "periodos", notes = "", response = PeriodoDTO.class, tags = {
			"periodos", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Periodo y lista de fechas", response = PeriodoDTO.class) })

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> get() {
		PeriodoDTO periodoDTO = this.periodoServicio.get();
		this.periodoServicio.completar(periodoDTO);

		return new ResponseEntity<Object>(periodoDTO, HttpStatus.OK);
	}
}
