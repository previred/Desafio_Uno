package com.previred.periodos.gddfaltantes.utils;

import com.previred.periodos.gddfaltantes.model.Periodo;
import com.previred.periodos.gddfaltantes.model.PeriodoFaltante;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PeriodoUtils {
    private final Periodo periodo;

    public PeriodoUtils(Periodo periodo) {
        this.periodo = periodo;
    }

    public PeriodoFaltante getPeriodoFaltantes() {
        List<LocalDate> fechas = periodo.getFechas();
        List<LocalDate> fechasFaltantes = new ArrayList<>();

        long months = ChronoUnit.MONTHS.between(periodo.getFechaCreacion(), periodo.getFechaFin());
        fechasFaltantes.add(periodo.getFechaCreacion().withDayOfMonth(1));
        for (int i = 1; i < months; i++) {
            fechasFaltantes.add(
                    fechasFaltantes.get(i - 1).plusMonths(1));
        }

        fechasFaltantes.removeAll(fechas);

        PeriodoFaltante periodoFaltante = new PeriodoFaltante();
        periodoFaltante.setId(periodo.getId());
        periodoFaltante.setFechaCreacion(periodo.getFechaCreacion());
        periodoFaltante.setFechaFin(periodo.getFechaFin());
        periodoFaltante.setFechas(periodo.getFechas());
        periodoFaltante.setFechasFaltantes(fechasFaltantes);

        return periodoFaltante;
    }

}
