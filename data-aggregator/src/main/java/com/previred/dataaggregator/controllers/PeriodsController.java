package com.previred.dataaggregator.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.MediaType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.previred.dataaggregator.client.PeriodosClient;
import com.previred.dataaggregator.service.Periodo;
import com.previred.dataaggregator.tools.YearPeriods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/")
@Api(value = "PREVIRED", description = "Previred Periods API")
@EnableSwagger2
public class PeriodsController {

	@Autowired
	private PeriodosClient service;
	final static Logger log = Logger.getLogger(PeriodsController.class);

	@ApiOperation(value = "Get Periods", response = Periodo.class)
    @ApiResponses(value = { 
    @ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class) })
	@RequestMapping(path = "/api/1.0/periods", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Periodo> getPeriods() {

		Periodo periodo = new Periodo();

		try {

			periodo = service.getPeriods();

			YearPeriods yearPeriods = new YearPeriods();

			periodo.setFechasFaltantes(yearPeriods.completePeriods(periodo.getFechas(), periodo.getFechaCreacion(),
					periodo.getFechaFin()));

			return new ResponseEntity<Periodo>(periodo, HttpStatus.OK);
			
		} catch (Exception e) {
			log.error("Exception : " + e.getLocalizedMessage());
			return new ResponseEntity<Periodo>(periodo, HttpStatus.GATEWAY_TIMEOUT);
		}

	}

}
