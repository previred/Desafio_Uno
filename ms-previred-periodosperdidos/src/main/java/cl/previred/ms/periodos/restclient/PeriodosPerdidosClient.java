package cl.previred.ms.periodos.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.previred.ms.periodos.dtos.SalidaAPIDTO;
import cl.previred.ms.periodos.exceptions.PreviredException;

@FeignClient(name="PeriodosPerdidosClient", url="${rest.endpoints.periodosperdidos.url}")
public interface PeriodosPerdidosClient {

    @GetMapping(value = "/periodos/api", produces = "application/json")
    SalidaAPIDTO periodos() throws PreviredException; 
}
