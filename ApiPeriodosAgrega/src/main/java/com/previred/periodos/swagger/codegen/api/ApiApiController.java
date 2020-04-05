package com.previred.periodos.swagger.codegen.api;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.servicio.PeriodosAgregadoService;
import com.previred.periodos.swagger.codegen.model.Periodo;

@Controller
public class ApiApiController implements ApiApi {

	Logger log = LoggerFactory.getLogger(ApiApiController.class);
	
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    @Autowired
    private PeriodosAgregadoService periodosService;

    @org.springframework.beans.factory.annotation.Autowired
    public ApiApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.ofNullable(objectMapper);
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Periodo> periodosAgregado(@RequestParam String inicio, @RequestParam String fin) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {//NOSONAR
                try {
                	//cliente
                	WebClient client1 = WebClient.builder().baseUrl("http://localhost:8081").build();
                	Periodo salida = client1.get().uri("/periodos/api?inicio="+inicio+"&fin="+fin).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Periodo.class).block();
                	
                    Periodo detalle = periodosService.getPeriodos(salida);
                    return new ResponseEntity<>(detalle, HttpStatus.OK);
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/xml", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
