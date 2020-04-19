package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary;

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase;
import com.previred.periodos.determinafaltantes.core.PeriodoConsFaltantes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/periodos/perdidos")
@RequiredArgsConstructor
public class PeriodosPerdidosController {
    private final DeterminarPeriodosPerdidosUseCase service;

    @GetMapping(value = {"","/"})
    public PeriodoConsFaltantes getPeriodosPerdidos() {
        return service.calcular();
    }
}
