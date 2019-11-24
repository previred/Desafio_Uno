package cl.previred.desafio.controller;

import cl.previred.desafio.component.Period;
import cl.previred.desafio.service.PeriodServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "API", description = "API - Rest Controller")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success|OK"),
        @ApiResponse(code = 401, message = "not authorized!"),
        @ApiResponse(code = 403, message = "forbidden!!!"),
        @ApiResponse(code = 404, message = "not found!!!") })
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@RequestMapping("/api/v1")
public class PeriodController {


    @Autowired
    @Qualifier("periodService")
    private PeriodServiceImpl periodService;

    @ApiOperation(value = "Recupera los periodos faltantes recuperados desde GDD")
    @GetMapping("/periods")
    public ResponseEntity<?> getPeriods() {
        Period period = periodService.getAllPeriods();
        return period.getFechaCreacion() != null
                ? new ResponseEntity<>(period, HttpStatus.OK)
                : new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

}
