package com.previred.gdd.rest.controller;

import com.previred.gdd.rest.model.GDD;
import com.previred.gdd.rest.service.GddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
public class GddController {

    @Autowired
    GddService service;

    @GetMapping("/api/fechas/fechaCreacion/{fechaDesde}/fechaFin/{fechaHasta}")
    public GDD fechas(@PathVariable String fechaDesde,
                      @PathVariable String fechaHasta) {
        try{
            LocalDate dateFrom = LocalDate.parse(fechaDesde);
            LocalDate dateTo = LocalDate.parse(fechaHasta);
            if(dateTo.isBefore(dateFrom)){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "FechaFin es Mayor a FechaCreacion", new Exception("FechaFin es Mayor a FechaCreacion"));
            }
            return service.generateData(dateFrom, dateTo);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Se ha producido un error", ex);
        }
    }
}
