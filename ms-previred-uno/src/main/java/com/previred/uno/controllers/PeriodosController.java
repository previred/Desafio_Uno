package com.previred.uno.controllers;

import com.previred.uno.exceptions.PreviredException;
import com.previred.uno.models.periodos.Periodos;
import com.previred.uno.services.impl.PeriodosServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase de Controlador de MS /periodos
 *
 * @author pvillar
 */

@RestController
@Slf4j
@Api(description = "Se obtienen periodos con meses faltantes")
@RequestMapping(value = "/periodos")
public class PeriodosController {

    /**
     * Controler
     */

    private static final String URL = "/periodos";
    private final PeriodosServiceImpl service;

    @Autowired
    public PeriodosController(PeriodosServiceImpl service) {
        this.service = service;
    }

    @RequestMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    @ApiOperation("Entrega un periodo el cual contiene fecha inicial, fecha final, lista de fechas y una lista con las fechas faltantes.")
    public ResponseEntity<Periodos> periodos() {
        try {
            log.info("INICIO ms-previred-uno - {} GET", URL);
            Periodos periodos = this.service.obtenerPeriodos();

            log.info("FIN ms-previred-uno - {} GET OK, Respuesta entregada: {}", URL, periodos);
            return new ResponseEntity<>(periodos, HttpStatus.OK);

        } catch (PreviredException previredException) {
            log.error("FIN ms-previred-uno - {} GET NOK - {}", URL, previredException.toString(), previredException);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception exception) {
            log.error("FIN ms-previred-uno - {} GET NOK", URL, exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}