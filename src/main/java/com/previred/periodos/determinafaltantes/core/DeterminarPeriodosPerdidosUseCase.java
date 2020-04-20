package com.previred.periodos.determinafaltantes.core;

public interface DeterminarPeriodosPerdidosUseCase {
    PeriodoConFaltantes calcular() throws PeriodoDataSourceNoDisponible;
}