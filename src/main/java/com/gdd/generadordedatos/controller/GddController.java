package com.gdd.generadordedatos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdd.generadordedatos.calculoFecha.CalcularService;
import com.gdd.generadordedatos.dto.FechaCalculadas;
import com.gdd.generadordedatos.dto.FechaEntradas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/api")
public class GddController {

    @Autowired
    CalcularService calcularService;

    @PostMapping("/gdd")
    public ResponseEntity<Object> obtenerResultado(@RequestBody FechaEntradas fechaEntradas)
            throws ParseException, IOException {
        try {
            FechaCalculadas x = calcularService.getFecha(fechaEntradas);
            if(x != null) {
                ObjectMapper mapeador = new ObjectMapper();
                mapeador.writeValue(new File("Generador_Datos_Desafio_Uno.txt"), x);
                return new ResponseEntity<>(x, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("PROBLEMA AL REALIZAR EL CALCULO DE FECHAS", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
