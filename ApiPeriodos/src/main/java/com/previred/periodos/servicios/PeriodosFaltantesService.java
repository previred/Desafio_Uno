package com.previred.periodos.servicios;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodosFaltantes;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeriodosFaltantesService {

    public long getMonthsBetween(LocalDate fechaInicio, LocalDate fechaFin){
        final long between = ChronoUnit.MONTHS.between(fechaInicio, fechaFin);

        return between;
    }

    public PeriodosFaltantes getPeriodosFaltantes(Periodo periodo){
        PeriodosFaltantes faltantes = new PeriodosFaltantes();

        faltantes.setId(periodo.getId());
        faltantes.setFechaCreacion(periodo.getFechaCreacion());
        faltantes.setFechaFin(periodo.getFechaFin());
        faltantes.setFechas(periodo.getFechas());

        long between = getMonthsBetween(faltantes.getFechaCreacion(), faltantes.getFechaFin());
        long maxLenght = between - faltantes.getFechas().size();

        Set<LocalDate> fechas = new HashSet();
        LocalDate temp = faltantes.getFechaCreacion();
        int i = 0;
        while (fechas.size() <= maxLenght) {
            if(i < faltantes.getFechas().size()) {
                if(faltantes.getFechas().get(i).compareTo(temp) == 0) {
                    i++;
                }
                else {
                    fechas.add(temp);
                }
            }
            else {
                fechas.add(temp);
            }
            temp = temp.plusMonths(1);
        }

        faltantes.setFechasFaltantes(fechas.stream()
                .sorted()
                .collect(Collectors.toList()));
        //faltantes.setId(getMonthsBetween(faltantes.getFechaCreacion(), faltantes.getFechaFin()));

        return faltantes;
    }

}
