package com.previred.periodos.controller;

import com.previred.periodos.dto.PeriodoDto;
import com.previred.periodos.service.PeriodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeriodoControllerImpl implements PeriodoController {

    private PeriodoService periodoService;

    @Autowired
    private PeriodoControllerImpl(PeriodoService periodoService){
        this.periodoService = periodoService;
    }

    @Override
    public PeriodoDto obtenerPeriodosPerdidos() {
        return periodoService.obtenerPeriodosPerdidos();
    }
}
