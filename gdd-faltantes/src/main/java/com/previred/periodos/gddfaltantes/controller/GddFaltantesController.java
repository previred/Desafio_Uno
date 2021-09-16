package com.previred.periodos.gddfaltantes.controller;

import com.previred.periodos.gddfaltantes.model.PeriodoFaltante;
import com.previred.periodos.gddfaltantes.service.PeriodosFaltantesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GddFaltantesController {

    @Autowired
    private PeriodosFaltantesService periodosFaltantesService;

    @Operation(summary = "Obtiene los periodos faltantes luego de invocar el servicio GDD")
    @RequestMapping(value = "/periodos-faltantes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PeriodoFaltante>  fechasFaltantes() {
        try {
            PeriodoFaltante periodoFaltante = periodosFaltantesService.getPeriodoFaltante();
            ResponseEntity<PeriodoFaltante> respuesta = new ResponseEntity<>(periodoFaltante, HttpStatus.OK);
            return respuesta;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
