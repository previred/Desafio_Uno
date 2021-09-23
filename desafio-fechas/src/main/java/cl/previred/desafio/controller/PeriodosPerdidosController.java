package cl.previred.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.desafio.client.model.Periodo;
import cl.previred.desafio.objects.ResponseObject;
import cl.previred.desafio.service.PeriodosPerdidosService;
import cl.previred.desafio.util.MessageConstants;
import cl.previred.desafio.util.ValidateUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/periodos")
public class PeriodosPerdidosController {
	
	@Autowired
	@Qualifier("periodosPerdidosService")
	private PeriodosPerdidosService periodosPerdidosService;
	
    @ApiOperation(value = "Buscar periodos perdidos")
    @ApiResponses(value = { 
    		@ApiResponse(code = 200, message = "OK", response = Object.class),
    		@ApiResponse(code = 201, message = "No Content", response = String.class),
    		@ApiResponse(code = 500, message = "Internal Error", response = String.class)})
	@GetMapping(value = "/obtenerPeriodosPerdidos", produces = {"application/json"})
	public ResponseEntity<Object> periodosPerdidos(){
    	
    	ResponseEntity<Object> response=null;
		
		Periodo peridoGDD = periodosPerdidosService.obtenerPeriodosGDD();
		if(null == peridoGDD || ValidateUtils.validateGDDResponse(peridoGDD).equals(Boolean.FALSE)) {
			response = new ResponseEntity<>(MessageConstants.MSG_ERROR_GDD, HttpStatus.NO_CONTENT);
		}else {
			
			ResponseObject resp = periodosPerdidosService.obtenerPeriodosPerdidos(peridoGDD);			
			if(null == resp) {
				response = new ResponseEntity<>(MessageConstants.MSG_ERROR_CALCULO_PERIODOS, HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				response = new ResponseEntity<>(resp, HttpStatus.OK);
			}			
		}
		return response;
	}
	

}
