package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary;

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase;
import com.previred.periodos.determinafaltantes.core.PeriodoConsFaltantes;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PeriodosPerdidosController {
    private final DeterminarPeriodosPerdidosUseCase service;

    public PeriodoConsFaltantes getPeriodosPerdidos() {
        return service.calcular();
    }
}
