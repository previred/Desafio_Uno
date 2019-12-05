package cl.previred.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cl.previred.app.bean.PeriodoBean;
import cl.previred.app.service.APIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST})
@RequestMapping("periodo")
@Api(value = "Obtener Periodos", description = "Api que devuelve los periodos faltantes entre dos fechas ")

public class PeriodoController {
	@Autowired
	APIService service;
	
	/*
	 * Devuelve los periodos faltantes entre dos fechas
	 * @return PeriodoBean.
	 * 
	 */
	

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Returns missing periods / Devuelve los periodos faltantes", notes = "Return Json with ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = String.class),
			@ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })

	public String getPeriodos(@RequestBody PeriodoBean obj ) throws JsonProcessingException {
		return service.getMissingPeriods(obj);
	}

}
