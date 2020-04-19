package com.previred.periodos.determinafaltantes.core;

public class PeriodoDataSourceNoDisponible extends RuntimeException{
    public PeriodoDataSourceNoDisponible() {
    }

    public PeriodoDataSourceNoDisponible(String message) {
        super(message);
    }
}
