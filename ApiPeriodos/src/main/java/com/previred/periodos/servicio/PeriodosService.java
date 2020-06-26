package com.previred.periodos.servicio;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.tools.RandomDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author mgonzalez@previred.com
 */
@Service
public class PeriodosService {

    private final static int MIN = 90;
    private final static int MAX = 100;

    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public Periodo getPeriodos() {
        //Se asguran almenos 100 fechas distintas 2004 - 1013 
        RandomDate fechaInicial = new RandomDate(LocalDate.of(1980, 1, 1), LocalDate.of(2004, 1, 1));
        RandomDate fechaFin = new RandomDate(LocalDate.of(2013, 2, 1), LocalDate.of(2019, 1, 1));

        Periodo periodo = new Periodo();
        periodo.setId(1L);
        periodo.setFechaCreacion(fechaInicial.nextDate());
        periodo.setFechaFin(fechaFin.nextDate());
        RandomDate fechaPeriodos = new RandomDate(periodo.getFechaCreacion(), periodo.getFechaFin());

        Random aleatorio = new Random();
        int cantidadPeriodos = aleatorio.nextInt((MAX - MIN) + 1) + MIN;
        Set<LocalDate> fechas = new HashSet();
        while (fechas.size() <= cantidadPeriodos) {            
            fechas.add(fechaPeriodos.nextDate());
        }
        periodo.setFechas(fechas.stream()
                .sorted()
                .collect(Collectors.toList()));

        return periodo;
    }
}
