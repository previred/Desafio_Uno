package com.previred.periodos.desafio.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by sati on 24-03-20.
 */
@SpringBootTest
public class LocalDateServiceTest {

    @Autowired
    private LocalDateService localDateService;

    @Test
    public void totalFechasTest(){
        int fechasCalculadas = localDateService.totalFechas(LocalDate.parse("1968-08-01"),LocalDate.parse("1971-06-01"));
        int fechasTotales = 35;
        assertEquals(fechasTotales, fechasCalculadas);

        fechasCalculadas = localDateService.totalFechas(LocalDate.parse("2020-08-01"),LocalDate.parse("2020-12-01"));
        fechasTotales = 5;
        assertEquals(fechasTotales, fechasCalculadas);

        fechasCalculadas = localDateService.totalFechas(LocalDate.parse("1986-08-01"),LocalDate.parse("2000-08-01"));
        fechasTotales = 169;
        assertEquals(fechasTotales, fechasCalculadas);
    }

    @Test
    public void totalFechasIliegalArgument(){
        assertThrows(IllegalArgumentException.class,()->
                localDateService.totalFechas(LocalDate.parse("1971-06-01"),LocalDate.parse("1968-08-01")));

    }


    @Test
    public void filtrarFechasTest(){
        int totalFechas = 7;
        List<LocalDate> fechas = localDateService.listaFechas(LocalDate.parse("1968-08-01"),LocalDate.parse("1971-06-01"));
        List<LocalDate>fechasFiltrar = localDateService.listaFechas(LocalDate.parse("1968-12-01"),LocalDate.parse("1971-03-01"));
        List<LocalDate>fechasFiltradas = localDateService.filtrarFechas(fechas,fechasFiltrar);
        assertEquals(totalFechas, fechasFiltradas.size());
    }

}