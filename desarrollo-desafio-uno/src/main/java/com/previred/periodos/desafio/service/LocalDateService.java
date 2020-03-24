package com.previred.periodos.desafio.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sati on 23-03-20.
 */
@Service
public class LocalDateService {

    public static final int MESES_X_ANIO = 12;
    public static final int FECHA_INICIAL = 1;

    public Integer totalFechas(LocalDate fechaInicial, LocalDate fechaFinal){
        if(!fechaInicial.isBefore(fechaFinal))
            throw new IllegalArgumentException("la fechaInicial debe ser mayor a la fechaFinal");
        return this.totalMeses(fechaInicial, fechaFinal)+ FECHA_INICIAL;
    }

    public List<LocalDate> listaFechas(LocalDate fechaInicial, LocalDate fechaFinal){
        Integer totalMeses = this.totalMeses(fechaInicial,fechaFinal);
        List<LocalDate>fechas = new ArrayList<>();
        fechas.add(fechaInicial);
        for (long contador = 1; contador<=totalMeses; contador++ ){
            fechas.add(fechaInicial.plusMonths(contador));
        }
        return fechas;
    }

    public Integer totalMeses(LocalDate fechaInicial, LocalDate fechaFinal){
        Period period = Period.between(fechaInicial, fechaFinal);
        return (period.getYears()* MESES_X_ANIO)+period.getMonths();
    }

    public List<LocalDate> filtrarFechas(List<LocalDate>lista, List<LocalDate>listaFiltro){
        List<LocalDate>listaFiltrada = lista.stream().filter(
                e -> (listaFiltro.stream().filter(d -> d.isEqual(e)).count())<1
        ).collect(Collectors.toList());

        return listaFiltrada;
    }
}
