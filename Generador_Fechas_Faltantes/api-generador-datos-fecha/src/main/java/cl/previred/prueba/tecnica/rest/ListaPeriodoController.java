package cl.previred.prueba.tecnica.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.prueba.tecnica.rest.response.PeriodoResponse;
import cl.previred.prueba.tecnica.services.GetPeriodosService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/periodos")
@Slf4j
public class ListaPeriodoController {

	/**
	 * services
	 */
	private GetPeriodosService getPeriodosServices;

	@Autowired
	public ListaPeriodoController(GetPeriodosService getPeriodosServices) {
		this.getPeriodosServices = getPeriodosServices;
	}

	@ApiOperation(value = "Lista de periodos faltantes", nickname = "periodos faltantes", notes = "", response = PeriodoResponse.class, tags = {
			"Periodos Faltantes", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Lista de fechas faltantes", response = PeriodoResponse.class) })
	@GetMapping(value = "/list", produces = { "application/json" })
	public ResponseEntity<PeriodoResponse> getDocuments() {
		try {
			ResponseEntity<PeriodoResponse> reponseEntity = getPeriodosServices.getPeriodos();
			return reponseEntity;
		} catch (Exception e) {
			log.error("Error general en API TEST ", e);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
