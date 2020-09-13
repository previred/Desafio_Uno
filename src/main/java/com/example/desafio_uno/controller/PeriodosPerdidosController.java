package com.example.desafio_uno.controller;

import com.example.desafio_uno.entity.PeriodosPerdidos;
import com.example.desafio_uno.service.PeriodosPerdidosImple;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class PeriodosPerdidosController {

    @Autowired
    private PeriodosPerdidosImple service;

    @ApiOperation(value = "Calculo de periodos perdidos")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> periodosPerdidos(){
        ResponseEntity<?> response;
        try {
            PeriodosPerdidos data = this.service.calcularPeriodosPerdidos();
            response = new ResponseEntity<>(data, HttpStatus.OK);

        } catch (Exception ex) {
            response = new ResponseEntity<>(mensajeError(ex.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public String mensajeError(String msjPersonalizado) {
        return "{\"Error\":\""+msjPersonalizado+"\"}";
    }
}
