package com.previred.periodosPerdidos.Helpers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Matias Arce on 11/23/2019.
 */
public class Utils {

    /**
     * Entrega las fechas de 1 de cada mes entre un rango de 2 fechas
     * @param fechaInicial
     * @param fechaFinal
     * @return
     */
    public static List<LocalDate> getFechasMesesEntreFechas(
            LocalDate fechaInicial, LocalDate fechaFinal) {
        long numOfMonthsBetween = ChronoUnit.MONTHS.between(fechaInicial, fechaFinal.plusMonths(1));

        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfMonthsBetween)
                .mapToObj(i -> fechaInicial.plusMonths(i))
                .collect(Collectors.toList());
    }
}
