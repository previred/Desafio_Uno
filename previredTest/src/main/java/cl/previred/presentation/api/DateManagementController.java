package cl.previred.presentation.api;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import cl.previred.domain.error.InternalBussinesException;
import cl.previred.presentation.api.model.PeriodoResponse;
import cl.previred.presentation.api.model.error.ApiErrorResponse;

/**
 * @author wmunoz
 */
@Validated
@RestController
@Slf4j
@RequestMapping("/v1/date_management")
public class DateManagementController{
	 
	private final DateManagementFacade dateManagementFacade;

    public DateManagementController(DateManagementFacade dateManagementFacade) {
        this.dateManagementFacade = dateManagementFacade;
    }
    
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operacion correcta.", response = PeriodoResponse.class),
            @ApiResponse(code = 422, message = "Error en algun elemento del request.", response = ApiErrorResponse.class),
            @ApiResponse(code = 404, message = "Recurso no encontrado", response = ApiErrorResponse.class),
            @ApiResponse(code = 500, message = "Servicio Interno no disponible", response = ApiErrorResponse.class),
            @ApiResponse(code = 503, message = "Error en el proceso interno del servicio.", response = ApiErrorResponse.class)
    })
    @ApiOperation(value = "DateManagementController", notes = "Contiene un único método que realiza el filtrado de las fechas, según el servicio GDD")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PeriodoResponse> getGDDFilteredResponse() throws InternalBussinesException {    	
        	log.info("Se inicia el metodo de filtrado de fechas");
        	PeriodoResponse response = dateManagementFacade.filtered();  
        	log.info("Retorna de la opeacion de forma exitosa");
            return new ResponseEntity<PeriodoResponse>(response, HttpStatus.OK);      
    
    }


   
}
