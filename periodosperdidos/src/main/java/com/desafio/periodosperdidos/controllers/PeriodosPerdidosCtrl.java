package com.desafio.periodosperdidos.controllers;

import com.desafio.periodosperdidos.io.ResponsePeriodosPerdidos;
import com.desafio.periodosperdidos.services.IPeriodosPerdidosServ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = { "PeriodosPerdidos" })
public class PeriodosPerdidosCtrl {

    @Autowired
    private IPeriodosPerdidosServ iPeriodosPerdidosServ;

    @GetMapping("periodosperdidos")
    @ApiOperation(value = "Consulta las gechas de un servicio remoto y completa las fechas faltantes")
    public ResponseEntity<ResponsePeriodosPerdidos> periodosPerdidos (){
        return iPeriodosPerdidosServ.getPeriodosPerdidos();
    }
}
