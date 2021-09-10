package cl.previred.gdd.controllers;

import cl.previred.gdd.dto.GddRequest;
import cl.previred.gdd.dto.GddResponse;
import cl.previred.gdd.services.IGddService;
import cl.previred.gdd.services.ServiceException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Controller que expone el servicio /rest/gdd el cual calcula los períodos faltantes
 */
@RestController
public class GddController {

  @Autowired
  private IGddService gddService;

  @ApiOperation(value = "Servicio que calcula las fechas faltantes en un período determinado.", notes = "Servicio que calcula las fechas faltantes en un período determinado")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "OK", examples =
                  @Example(value = {@ExampleProperty(mediaType = "application/json", value = "{\"id\": 6, \"fechaCreacion\": \"1969-03-01\", \"fechaFin\": \"1970-01-01\", \"fechas\": [\"1969-03-01\", \"1969-05-01\", \"1969-09-01\", \"1970-01-01\"]}")})
          )
  })
  @PostMapping(path = "/rest/gdd")

  public ResponseEntity<GddResponse> gdd(@RequestBody GddRequest request) throws ServiceException {
    GddResponse gddResponse = gddService.calculateMissingPeriods(request);
    return ResponseEntity.ok(gddResponse);
  }

}
