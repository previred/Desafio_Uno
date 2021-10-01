package com.nibor.examen.lostperiod.services;

import com.nibor.examen.lostperiod.dtos.PeriodoGeneradoDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneradorDatosServiceTest {

    @Autowired
    private GeneradorDatosService service;

    @Test
    public void getPeriodosGeneradoTest(){
        PeriodoGeneradoDTO periodo = service.getPeriodosGenerado();
        assertNotNull(periodo);
    }
}