package com.previred.periodos.determinafaltantes.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Periodo {
    private int id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    @Builder.Default
    private SortedSet<LocalDate> fechas = new TreeSet<>();
    public static final Periodo PERIODO_NULO = Periodo.builder()
            .id(0)
            .fechaCreacion(LocalDate.of(0, 1, 1))
            .fechaFin(LocalDate.of(0, 1, 1))
            .build();


}
