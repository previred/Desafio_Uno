package cl.previred.desafio.uno.nivel3.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.desafio.uno.nivel3.api.config.SwaggerApiDocConstants;
import cl.previred.desafio.uno.nivel3.api.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel3.api.exceptions.DesafioUnoInformedException;
import cl.previred.desafio.uno.nivel3.api.service.DesafioUnoService;
import cl.previred.desafio.uno.nivel3.api.service.OneLevelCallGDDService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Periodos")
public class McerdaPeriodoController {

	@Autowired
	private OneLevelCallGDDService oneLevelCallGDDService;

	@Autowired
	private DesafioUnoService desafioUnoService;

	@GetMapping(path = "/periodos")
	@ApiOperation(value = SwaggerApiDocConstants.MCERDA_PERIODOS_CONTROLLER_GET,
    notes = SwaggerApiDocConstants.MCERDA_PERIODOS_CONTROLLER_NOTE_GET)
	public ResponseEntity<DesafioUnoFechasDTO> getPeridodos() throws DesafioUnoInformedException {
		DesafioUnoFechasDTO periodos = oneLevelCallGDDService.callGetGDD();
		periodos.setFechas(desafioUnoService.getFechasFaltantes(periodos));
		desafioUnoService.print(periodos);
		return new ResponseEntity<>(periodos, HttpStatus.OK);
	}

}
