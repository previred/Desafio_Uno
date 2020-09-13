package com.example.desafio_uno.service;

import com.example.desafio_uno.client.PreviredClient;
import com.example.desafio_uno.entity.PeriodosPerdidos;
import com.example.desafio_uno.entity.Previred;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PeriodosPerdidosImple implements IPeriodosPerdidos {

    @Autowired
    private PreviredClient previredClient;

    @Override
    public PeriodosPerdidos calcularPeriodosPerdidos() {
        //Consumo Generador de datos previred
        Previred data = this.previredClient.obtenerFechasPrevired();

        //Calculo periodos Perdidos
        PeriodosPerdidos periodosPerdidos = new PeriodosPerdidos();

        periodosPerdidos.setId(data.getId());
        periodosPerdidos.setFechaCreacion(data.getFechaCreacion());
        periodosPerdidos.setFechaFin(data.getFechaFin());
        periodosPerdidos.setFechas(data.getFechas());

        List<LocalDate> fechasFaltantes = new ArrayList<>();
        for(LocalDate date = data.getFechaCreacion(); date.isBefore(data.getFechaFin().plusMonths(1)); date = date.plusMonths(1)) {
            if (!data.getFechas().contains(date)){
                fechasFaltantes.add(date);
            }
        }
        periodosPerdidos.setFechasFaltantes(fechasFaltantes);

        return periodosPerdidos;
    }
}
