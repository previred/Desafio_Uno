package com.previred.prueba.controllers;

import com.previred.prueba.request.DatosEntrada;
import com.previred.prueba.response.DatosSalida;
import com.previred.prueba.service.PruebaPreviredFechasService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@EnableSpringDataWebSupport
public class PruebaPreviredFechasController {

    private static final Logger log = Logger.getLogger(PruebaPreviredFechasController.class);

    @Autowired
    PruebaPreviredFechasService pruebaPreviredFechasService;

    @RequestMapping(value = "/fecha", method = RequestMethod.GET)
    public ResponseEntity<Object> obtieneFecha() throws Exception {
        Date date = new Date();
        return new ResponseEntity<Object>(date, HttpStatus.OK);
    }

    @RequestMapping(value = "/fechas/detalle", method = RequestMethod.PUT)
    public ResponseEntity<Object> obtieneDetalleFechas(@RequestBody DatosEntrada datosEntrada) throws Exception {
        Object obj = null;
        try{
            if(datosEntrada == null){
                return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
            }else{
                DatosSalida datosSalida = new DatosSalida();

                datosSalida = pruebaPreviredFechasService.obtieneDetalleFechas(datosEntrada);
                 obj = datosSalida;
                return new ResponseEntity<Object>(obj, HttpStatus.OK);
            }
        }catch (Exception e){
             obj = e.getCause();
            return new ResponseEntity<Object>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }
}