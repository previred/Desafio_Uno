package com.desafio.periodosperdidos.services;

import com.desafio.periodosperdidos.io.ResponsePeriodosPerdidos;
import org.springframework.http.ResponseEntity;

public interface IPeriodosPerdidosServ {
    ResponseEntity<ResponsePeriodosPerdidos> getPeriodosPerdidos();
}
