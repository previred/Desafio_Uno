package com.previred.periodos.desafio.controller;

import com.previred.periodos.desafio.model.Periodo;
import com.previred.periodos.desafio.service.PeriodoDesafioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sati on 24-03-20.
 */
@RestController
public class PeriodoDesafioController {

    @Autowired
    private PeriodoDesafioService periodoDesafioService;

    @ApiOperation(value = "lista fechas de un rango aleatorio ", response = Periodo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Periodo y lista de fechas", response = Periodo.class)
    })
    @GetMapping(path = "/periodo/aleatorio")
    public ResponseEntity<Periodo>periodoAleatorio(){
        Periodo periodos = periodoDesafioService.periodos();
        ResponseEntity<Periodo> respuesta = new ResponseEntity<>(periodos, HttpStatus.OK);
        return respuesta;
    }
}
