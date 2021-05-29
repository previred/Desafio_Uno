package com.desafio.periodosperdidos.services;

import com.desafio.periodosperdidos.entites.ResponseGDD;
import org.springframework.http.ResponseEntity;

public interface IPedidosService {
    ResponseEntity<ResponseGDD> getPeriodos();
}
