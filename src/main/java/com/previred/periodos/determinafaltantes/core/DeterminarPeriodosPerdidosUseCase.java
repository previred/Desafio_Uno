package com.previred.periodos.determinafaltantes.core;

public interface DeterminarPeriodosPerdidosUseCase {
    PeriodoConsFaltantes calcular() throws PeriodoDataSourceNoDisponible;
}