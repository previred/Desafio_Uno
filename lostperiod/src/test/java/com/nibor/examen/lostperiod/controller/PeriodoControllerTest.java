package com.nibor.examen.lostperiod.controller;

import com.nibor.examen.lostperiod.dtos.PeriodoPerdidoDTO;
import com.nibor.examen.lostperiod.services.PeriodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PeriodoController.class)
class PeriodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PeriodoService periodoService;

    @Test
    void getPeriodosFaltantes() throws Exception {

        PeriodoPerdidoDTO periodoResponse = new PeriodoPerdidoDTO();
        periodoResponse.setId(1);
        periodoResponse.setFechaCreacion("1989-11-01");
        periodoResponse.setFechaFin("1990-02-01");
        List<String> fechas = new ArrayList<>();
        fechas.add("1989-11-01");
        fechas.add("1989-12-01");
        periodoResponse.setFechas(fechas);
        List<String> fechasFaltantes = new ArrayList<>();
        fechasFaltantes.add("1990-01-01");
        fechasFaltantes.add("1990-02-01");
        periodoResponse.setFechasFaltantes(fechasFaltantes);

        Mockito.when(periodoService.getPeriodosPerdidos()).thenReturn(periodoResponse);

        String url = "/periodo-faltante";
        MvcResult response = mvc.perform(get(url)).andExpect(status().isOk()).andReturn();
        response.getResponse().getContentAsString();
    }
}