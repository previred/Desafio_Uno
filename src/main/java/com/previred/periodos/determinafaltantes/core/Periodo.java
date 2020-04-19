package com.previred.periodos.determinafaltantes.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
public class Periodo {
    private int id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    @Builder.Default
    private List<LocalDate> fechas = new ArrayList<>();
    public static final Periodo PERIODO_NULO = Periodo.builder()
            .id(0)
            .fechaCreacion(LocalDate.of(0, 1, 1))
            .fechaFin(LocalDate.of(0, 1, 1))
            .build();


}
