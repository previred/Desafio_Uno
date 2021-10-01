package com.nibor.examen.lostperiod.services;

import com.nibor.examen.lostperiod.dtos.PeriodoGeneradoDTO;
import com.nibor.examen.lostperiod.dtos.PeriodoPerdidoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeriodoServiceTest {

    @Autowired
    private PeriodoService service;

    @MockBean
    private GeneradorDatosService generadorDatosService;

    @Test
    public void getPeriodosPerdidoTest(){

        List<String> fechas = new ArrayList<>();
        PeriodoGeneradoDTO periodo = new PeriodoGeneradoDTO();

        periodo.setFechaCreacion("1995-10-01");
        periodo.setId(1);
        periodo.setFechaFin("1997-02-01");
        fechas.add("1995-12-01");
        fechas.add("1996-12-01");
        fechas.add("1997-02-01");
        periodo.setFechas(fechas);
        when(generadorDatosService.getPeriodosGenerado()).thenReturn(periodo);
        PeriodoPerdidoDTO periodoPerdidos = service.getPeriodosPerdidos();
        assertEquals(14, periodoPerdidos.getFechasFaltantes().size());
    }

    @Test
    public void getPeriodosPerdidoNOKTest(){

        List<String> fechas = new ArrayList<>();
        PeriodoGeneradoDTO periodo = new PeriodoGeneradoDTO();

        periodo.setFechaCreacion("1996-10-01");
        periodo.setId(1);
        periodo.setFechaFin("1997-04-01");
        fechas.add("1996-11-01");
        fechas.add("1996-12-01");
        fechas.add("1997-02-01");
        fechas.add("1997-03-01");
        periodo.setFechas(fechas);
        when(generadorDatosService.getPeriodosGenerado()).thenReturn(periodo);
        PeriodoPerdidoDTO periodoPerdidos = service.getPeriodosPerdidos();
        assertNotEquals(4, periodoPerdidos.getFechasFaltantes().size());
    }

}