package cl.desafio.previred.controller;


import cl.desafio.previred.dominio.Periodo;
import cl.desafio.previred.service.PeriodosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/periodosCompletos")
public class PeriodosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodosController.class);

    @Autowired
    PeriodosService periodosService;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping
    public ResponseEntity<Periodo> consultaPeriodos() {

        Periodo periodoIncompleto = restTemplate.getForObject("http://127.0.0.1:8080/periodos/api", Periodo.class);

        return new ResponseEntity<>(periodosService.consultaPeriodos(periodoIncompleto), HttpStatus.OK);
    }

}
