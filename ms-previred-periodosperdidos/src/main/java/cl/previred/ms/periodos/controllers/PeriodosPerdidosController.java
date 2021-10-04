package cl.previred.ms.periodos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.ms.periodos.dtos.MessageDTO;
import cl.previred.ms.periodos.dtos.SalidaPeriodosDTO;
import cl.previred.ms.periodos.exceptions.PreviredException;
import cl.previred.ms.periodos.services.utils.HeadersUtils;
import cl.previred.ms.periodos.services.PeriodosPerdidosService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("v1/peridodosperdidos")
public class PeriodosPerdidosController {

    static final Logger logger = LoggerFactory.getLogger(PeriodosPerdidosController.class);
    private PeriodosPerdidosService PeriodosPerdidosService;

	@Autowired
    public PeriodosPerdidosController(final PeriodosPerdidosService periodosPerdidosService) {
        this.PeriodosPerdidosService = periodosPerdidosService;
    }
	
	
	@ApiOperation(value = "", nickname = "PeriodosPerdidos", 
	notes = "Proceso encargado de calcular los periodos Perdidos de API de Periodos", tags={ "Periodos", })	
	@ApiResponses(value = { 
			
	@ApiResponse(code = 200, message = "Resultado OK"),
	@ApiResponse(code = 401, message = "No autorizado", response = MessageDTO.class),
	@ApiResponse(code = 404, message = "Endpoint no encontrado", response = MessageDTO.class),
	@ApiResponse(code = 500, message = "Acceso inválido al recurso", response = MessageDTO.class)
	
	})
	@RequestMapping(value = "/",
		    produces = { "application/json" }, 
		    method = RequestMethod.GET)
    public ResponseEntity<SalidaPeriodosDTO> periodosPerdidos() throws PreviredException {
        
		logger.info("Inicio endpoint v1/peridodosperdidos");
		
        SalidaPeriodosDTO controllerResponse = PeriodosPerdidosService.periodosPerdidos();
        
        logger.info("Fin endpoint v1/peridodosperdidos");
        
        return new ResponseEntity<>(controllerResponse, HeadersUtils.getGenericHeaders(), HttpStatus.OK);
    }
}
