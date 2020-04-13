package com.previred.periodos.service;

import com.previred.periodos.client.ApiPeriodoClient;
import com.previred.periodos.dto.PeriodoDto;
import com.previred.periodos.exception.FechaInicioMayorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Value("${error.fechaInicioMayor}")
    private String errorFechaInicioMayor;

    private ApiPeriodoClient apiPeriodoClient;

    @Autowired
    private PeriodoServiceImpl(ApiPeriodoClient apiPeriodoClient){
        this.apiPeriodoClient = apiPeriodoClient;
    }

    @Override
    public PeriodoDto obtenerPeriodosPerdidos() {

        PeriodoDto apiResponse = apiPeriodoClient.ejecutar();

        validarFechaCreacionSeaMenor(apiResponse.getFechaCreacion(),apiResponse.getFechaFin());

        LocalDate fechaCreacion = cambiarAPrimeroDeMes(apiResponse.getFechaCreacion());
        LocalDate fechaFin = cambiarAPrimeroDeMes(apiResponse.getFechaFin());
        Set<LocalDate> fechasAleatoreas = apiResponse.getFechas().stream().map(n -> cambiarAPrimeroDeMes(n)).collect(Collectors.toSet());

        Set<LocalDate> fechasTotales = fechasEntrePeriodos(fechaCreacion,fechaFin);
        Set<LocalDate> fechaFaltante = fechasFaltantes(fechasAleatoreas,fechasTotales);
        apiResponse.setFechasFaltantes(fechaFaltante);
        return apiResponse;
    }

    private LocalDate cambiarAPrimeroDeMes(LocalDate fecha){
        return fecha.withDayOfMonth(1);
    }
    private Set<LocalDate> fechasFaltantes(Set<LocalDate> fechasIniciales, Set<LocalDate> fechasTotales){
         return fechasTotales.
                 stream().
                 filter(fecha -> !fechasIniciales.contains(fecha)).
                 collect(Collectors.toSet());
    }
    private void validarFechaCreacionSeaMenor(LocalDate fechaCreacion, LocalDate fechaFin){
        if(fechaCreacion.isAfter(fechaFin)){
            throw new FechaInicioMayorException(errorFechaInicioMayor);
        }
    }

    private Set<LocalDate> fechasEntrePeriodos(LocalDate fechaCreacion, LocalDate fechaFin){

        Set<LocalDate> fechasTotales = new HashSet<>();
        long numeroMesesTotal = ChronoUnit.MONTHS.between(fechaCreacion, fechaFin);
        for (int incremental = 1; incremental< numeroMesesTotal + 1; incremental++) {
            fechasTotales.add(fechaCreacion.plusMonths(incremental));
        }
        return fechasTotales;
    }
}
