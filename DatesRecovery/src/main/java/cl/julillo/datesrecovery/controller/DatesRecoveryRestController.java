package cl.julillo.datesrecovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.julillo.datesrecovery.model.dto.PeriodoDTO;
import cl.julillo.datesrecovery.service.iRecuperarFechasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Dates Recovery", tags = { "Dates Recovery" })
@RestController
@ApiResponses(value = { 
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"), 
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 415, message = "Unsupported media type"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
public class DatesRecoveryRestController {

	@Autowired
	private iRecuperarFechasService reServ;

	@GetMapping("/recuperar-fechas")
	@ApiOperation(httpMethod = "GET", value = "get")
	public ResponseEntity<PeriodoDTO> recuperarFechas() {
		return ResponseEntity.ok(this.reServ.recuperarFechas());
	}

}
