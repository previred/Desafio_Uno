package cl.prueba.previred.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import cl.prueba.previred.model.GDFechas;
import cl.prueba.previred.service.GDFechasService;

@RestController
@RequestMapping("/gdd")
public class GDFechasController {

    @Autowired
    private GDFechasService service;

    @ApiOperation("Obtiene gdd")
    //@PostMapping(value = "/gdd")

    @GetMapping
    public GDFechas getAllCoins() throws ParseException {
        //@RequestHeader(value = "Accept") String "application/json"
        return service.getCoins();
    }
}


