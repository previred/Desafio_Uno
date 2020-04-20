package com.previred.periodos.determinafaltantes.core;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PeriodoConFaltantes extends Periodo{
    @Builder.Default
    private SortedSet<LocalDate> fechasFaltantes =  new TreeSet<>();
}
