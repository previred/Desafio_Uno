package com.example.demo.controller;

import com.example.demo.entity.Periodo;
import com.example.demo.entity.PeriodoCompleto;
import com.example.demo.service.PeriodoCompletoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/periodo")
@CrossOrigin(value={})//permite acceso desde otras ubicaciones
public class PeriodoCompletoController {
    @Autowired
    PeriodoCompletoService periodoService;

    @Autowired
    RestTemplate restTemplate;
    @ApiOperation(value="Devuelve el periodo generado por el GDD")
    @GetMapping()
    public ResponseEntity<?> periodo(){
        ResponseEntity<?> response;
        try{
            Periodo periodo = this.periodoService.devolverPeriodo();
            response = new ResponseEntity<>(periodo, HttpStatus.OK);
        }catch (Exception ex){
            response=new ResponseEntity<>(mensajeError(ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    @ApiOperation(value = "Devuelve el periodo y una lista con las fechas pendientes")
    @GetMapping ("/resultado")
    public ResponseEntity<?> periodoCompleto(){
        ResponseEntity<?> response;
        PeriodoCompleto periodoCompleto =this.periodoService.devolverPeriodoCompleto();
        try{
            response = new ResponseEntity<>(periodoCompleto, HttpStatus.OK);
        }catch (Exception ex){
            response=new ResponseEntity<>(mensajeError(ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
    public String mensajeError(String msjPersonalizado) {
        return "{\"Error\":\""+msjPersonalizado+"\"}";
    }

}
