package cl.previred.gdd.controllers;

import cl.previred.gdd.dto.GddRequest;
import cl.previred.gdd.dto.GddResponse;
import cl.previred.gdd.services.IGddService;
import cl.previred.gdd.services.ServiceException;
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

  @PostMapping(path = "/rest/gdd")
  public ResponseEntity<GddResponse> gdd(@RequestBody GddRequest request) throws ServiceException {
    GddResponse gddResponse = gddService.calculateMissingPeriods(request);
    return ResponseEntity.ok(gddResponse);
  }

}
