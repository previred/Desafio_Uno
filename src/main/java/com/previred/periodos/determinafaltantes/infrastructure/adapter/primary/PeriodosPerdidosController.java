package com.previred.periodos.determinafaltantes.infrastructure.adapter.primary;

import com.previred.periodos.determinafaltantes.core.DeterminarPeriodosPerdidosUseCase;
import com.previred.periodos.determinafaltantes.core.PeriodoConsFaltantes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/periodos/perdidos")
@RequiredArgsConstructor
public class PeriodosPerdidosController {
    private final DeterminarPeriodosPerdidosUseCase service;

    @GetMapping
    @ApiOperation("Obtiene las fechas generadas y las fechas faltantes en el rango definido por el servicio de generacion de datos")
    public PeriodoConsFaltantes getPeriodosPerdidos() {
        return service.calcular();
    }
}
