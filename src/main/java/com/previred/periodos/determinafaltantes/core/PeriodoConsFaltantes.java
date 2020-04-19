package com.previred.periodos.determinafaltantes.core;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class PeriodoConsFaltantes extends Periodo{
    @Builder.Default
    private List<LocalDate> fechasFaltantes =  new ArrayList<>();
}
