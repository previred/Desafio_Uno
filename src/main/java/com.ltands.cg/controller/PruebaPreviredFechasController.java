package com.ltands.cg.controller;

import com.ltands.cg.response.DatosSalida;
import com.ltands.cg.service.PruebaPreviredFechasService;
import com.ltands.cg.request.DatosEntrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableSpringDataWebSupport
public class PruebaPreviredFechasController {

    @Autowired
    PruebaPreviredFechasService pruebaPreviredFechasService;

    @RequestMapping(value = "/fechas/detalle", method = RequestMethod.PUT)
    public ResponseEntity<DatosSalida> obtieneDetalleFechas(@RequestBody DatosEntrada datosEntrada) throws Exception {

        if(datosEntrada != null){
            return new ResponseEntity<DatosSalida>(HttpStatus.BAD_REQUEST);
        }else{
            DatosSalida datosSalida = new DatosSalida();

            datosSalida = pruebaPreviredFechasService.obtieneDetalleFechas(datosEntrada);
            return new ResponseEntity<DatosSalida>(datosSalida, HttpStatus.OK);
        }
    }
}