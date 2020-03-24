package com.previred.periodos.desafio.service;

import com.previred.periodos.desafio.model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by sati on 24-03-20.
 */
@Service
public class PeriodoDesafioService {

    @Autowired
    private ClienteRestGDDService clienteRestGDDService;

    @Autowired
    private LocalDateService localDateService;

    public Periodo periodos(){
        Periodo periodos = clienteRestGDDService.getPeriodos();
        LocalDate fechaCreacion = periodos.getFechaCreacion();
        LocalDate fechaFin = periodos.getFechaFin();
        List<LocalDate> fechas = periodos.getFechas();
        List<LocalDate> totalFechas = localDateService.listaFechas(fechaCreacion, fechaFin);
        List<LocalDate> fechasFaltantes = localDateService.filtrarFechas(totalFechas, fechas);
        periodos.setFechasFaltantes(fechasFaltantes);
        return periodos;
    }
}
