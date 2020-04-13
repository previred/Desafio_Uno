package com.previred.periodos.controller;

import com.previred.periodos.dto.PeriodoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;


@Api(value = "Servicio de periodos.", tags = {"Servicio de busqueda de periodos"})
public interface PeriodoController {

    @ApiOperation(value = "Periodos perdidos",
            notes = "listado de periodos perdidos en base a un listado inicial",
            httpMethod = "GET",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/perdidos")
    PeriodoDto obtenerPeriodosPerdidos();
}
