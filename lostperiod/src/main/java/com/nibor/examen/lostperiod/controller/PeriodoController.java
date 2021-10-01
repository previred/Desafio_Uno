package com.nibor.examen.lostperiod.controller;

import com.nibor.examen.lostperiod.dtos.PeriodoPerdidoDTO;
import com.nibor.examen.lostperiod.services.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeriodoController {

    @Autowired
    private PeriodoService periodoService;

    @GetMapping(value="/periodo-faltante")
    public ResponseEntity<PeriodoPerdidoDTO> getPeriodosFaltantes(){

        return ResponseEntity.ok(periodoService.getPeriodosPerdidos());
    }
}
