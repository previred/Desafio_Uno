package com.previred.periodos.servicio;

import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.tools.RandomDate;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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

    /**
     * Genera un Objetos periodos, los rangos de fechas van de 1980 a
     * 2019 el rango de lista de fechas en el periodo va desde 90 a 100
     *
     * @return
     */
    public Periodo getPeriodos(String inicio, String fin) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicial = LocalDate.parse(inicio,formatter);
        LocalDate fechaFin = LocalDate.parse(fin,formatter);

        Periodo periodo = new Periodo();
        periodo.setId(1L);
        periodo.setFechaCreacion(fechaInicial);
        periodo.setFechaFin(fechaFin);
        RandomDate fechaPeriodos = new RandomDate(periodo.getFechaCreacion(), periodo.getFechaFin());
        
        Period diff = Period.between(fechaInicial.withDayOfMonth(1),fechaFin.withDayOfMonth(1));
        
        Random aleatorio = new Random();
        int cantidadPeriodos = aleatorio.nextInt(diff.getMonths());
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
