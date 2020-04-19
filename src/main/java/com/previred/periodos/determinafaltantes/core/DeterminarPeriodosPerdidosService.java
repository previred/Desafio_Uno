package com.previred.periodos.determinafaltantes.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class DeterminarPeriodosPerdidosService implements DeterminarPeriodosPerdidosUseCase{
    public static final String LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA = "La consulta de las fechas del period aleatorio no fue satisfactoria";
    private final PeriodoPort periodoPort;

    @Override
    public PeriodoConsFaltantes calcular() throws PeriodoDataSourceNoDisponible {
        Periodo fechasPeriodoAleatorio = periodoPort.getFechasPeriodoAleatorio();
        if(fechasPeriodoAleatorio == Periodo.PERIODO_NULO){
            log.info(LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA);
            throw new PeriodoDataSourceNoDisponible(LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA);
        }

        return PeriodoConsFaltantes.builder()
                .id(fechasPeriodoAleatorio.getId())
                .fechaCreacion(fechasPeriodoAleatorio.getFechaCreacion())
                .fechaFin(fechasPeriodoAleatorio.getFechaFin())
                .fechas(fechasPeriodoAleatorio.getFechas())
                .build();
    }
}
