package com.previred.desafio.periodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/periodos/faltantes")
public class PeriodoController {

    private final PeriodoService periodoConFechasFaltantesService;

    @Autowired
    public PeriodoController(PeriodoService periodoConFechasFaltantesService) {
        this.periodoConFechasFaltantesService = periodoConFechasFaltantesService;
    }

    @GetMapping
    public Periodo getPeriodoConFechasFaltantes() {
        return periodoConFechasFaltantesService.getPeriodoConFechasFaltantes();
    }

}
