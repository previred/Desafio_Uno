package com.previred.uno.comunes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CLase de utilidades generales de la Api
 *
 * @author pvillar
 */

public class Utils {

    public Utils(){}

    /**
     * Obtener meses faltantes en lista ingresada, considerando una rango de fechas entre
     * una fecha de inicio y una de fin.
     *
     * @param fechaInicio fecha inicial del rango
     * @param fechaFin fecha final del rango
     * @param listaFechas lista de fechas entregadas
     * @return Lista de fechas no no existentes en las de entrada
     */

    public List<LocalDate> getMesesFaltantes(LocalDate fechaInicio, LocalDate fechaFin, List<LocalDate> listaFechas) {
        List<LocalDate> fechasFaltantes = new ArrayList<>();
        for (LocalDate fecha = fechaInicio; fecha.isBefore(fechaFin.plusMonths(1)); fecha = fecha.plusMonths(1)) {
            if (!listaFechas.contains(fecha)) {
                fechasFaltantes.add(fecha);
            }
        }
        return fechasFaltantes;
    }

}
