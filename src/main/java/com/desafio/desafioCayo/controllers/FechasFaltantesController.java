package com.desafio.desafioCayo.controllers;

import com.desafio.desafioCayo.bsd.BSDLeerArchivoInterface;
import com.desafio.desafioCayo.client.GeneradorDataDesafio;
import com.desafio.desafioCayo.dto.FechasGeneradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class FechasFaltantesController {

    @Autowired
    GeneradorDataDesafio gddService;

    @Autowired
    BSDLeerArchivoInterface fechasService;

    @GetMapping(value="/faltantes")
    public Object faltantes() {

        Map<String, Object> response = new HashMap<>();
        FechasGeneradorDTO result = new FechasGeneradorDTO();
        try {
            result = (fechasService.generarSalida(gddService.getGeneradorDD()));
        } catch (Exception e) {
            response.put("mensaje", "Error al llamar al servicio Generador DD");
            response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }
}
