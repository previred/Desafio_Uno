package com.previred.periodos.determinafaltantes.core;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@RequiredArgsConstructor
@Slf4j
public class DeterminarPeriodosPerdidosService implements DeterminarPeriodosPerdidosUseCase{
    public static final String LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA = "La consulta de las fechas del period aleatorio no fue satisfactoria";
    private final PeriodoPort periodoPort;

    @Override
    public PeriodoConFaltantes calcular() throws PeriodoDataSourceNoDisponible {
        Periodo fechasPeriodoAleatorio = periodoPort.getFechasPeriodoAleatorio();
        if(fechasPeriodoAleatorio == Periodo.PERIODO_NULO){
            log.info(LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA);
            throw new PeriodoDataSourceNoDisponible(LA_CONSULTA_DE_LAS_FECHAS_DEL_PERIOD_ALEATORIO_NO_FUE_SATISFACTORIA);
        }

        return PeriodoConFaltantes.builder()
                .id(fechasPeriodoAleatorio.getId())
                .fechaCreacion(fechasPeriodoAleatorio.getFechaCreacion())
                .fechaFin(fechasPeriodoAleatorio.getFechaFin())
                .fechas(fechasPeriodoAleatorio.getFechas())
                .fechasFaltantes(calcularFechasFaltantes(fechasPeriodoAleatorio))
                .build();
    }

    private SortedSet<LocalDate> calcularFechasFaltantes(@NonNull Periodo periodo) {
        SortedSet<LocalDate> faltantes = new TreeSet<>();
        for(LocalDate fecha = periodo.getFechaCreacion().plusMonths(1); fecha.isBefore(periodo.getFechaFin()); fecha = fecha.plusMonths(1)){
            if(!periodo.getFechas().contains(fecha)){
                faltantes.add(fecha);
            }
        }
        return faltantes;
    }
}
