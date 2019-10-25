package cl.hermann.periodos.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hylianred
 */
public class PeriodoUtils {

    public static List<LocalDate> obtenerEntreFechas(LocalDate fechaInicial, LocalDate fechaFinal) {
        List<LocalDate> listaFechas = new ArrayList<>();
        long mesesEntreFechas = ChronoUnit.MONTHS.between(
                fechaInicial.withDayOfMonth(1).plusMonths(1),
                fechaFinal.withDayOfMonth(1));

        if (mesesEntreFechas > 0) {
            for (int i = 0; i < mesesEntreFechas; i++) {
                listaFechas.add(fechaInicial.plusMonths(i+1));
            }
        }

        return listaFechas;
    }
}
