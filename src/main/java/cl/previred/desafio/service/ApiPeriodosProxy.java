package cl.previred.desafio.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Cliente Rest para consumir servicio GDD
 */
@FeignClient(name="api-periodos", url="localhost:8080")
public interface ApiPeriodosProxy {

    @GetMapping(value="/periodos/api", produces = "application/json")
    public ResponseEntity<Periodo> getPeriodos();

}
