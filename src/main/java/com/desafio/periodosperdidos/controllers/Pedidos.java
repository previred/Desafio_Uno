package com.desafio.periodosperdidos.controllers;

import com.desafio.periodosperdidos.entites.ResponseGDD;
import com.desafio.periodosperdidos.services.IPedidosService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Pedidos {

    @Autowired(required = false)
    private IPedidosService pedidosSRV;

    @RequestMapping(method = RequestMethod.GET, value = "pedidos")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 404, message = "URL not found")
            }
    )
    public ResponseEntity<ResponseGDD> fechas() {
        return pedidosSRV.getPeriodos();
    }
}
