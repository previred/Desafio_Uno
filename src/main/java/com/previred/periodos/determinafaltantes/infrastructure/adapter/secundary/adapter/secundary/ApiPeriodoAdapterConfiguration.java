package com.previred.periodos.determinafaltantes.infrastructure.adapter.secundary.adapter.secundary;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;

@Getter
@Setter
public class ApiPeriodoAdapterConfiguration {
    private URL url;
    private String formatoFecha;
    private LocalDate fechaPorDefecto;
}
