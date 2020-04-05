package com.previred.periodos.swagger.codegen.api;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.previred.periodos.servicio.PeriodosCheckService;
import com.previred.periodos.swagger.codegen.api.ApiApi;
import com.previred.periodos.swagger.codegen.model.PeriodoSol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller 
public class ApiApiController implements ApiApi {
    private static Logger log = LoggerFactory.getLogger(ApiApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private PeriodosCheckService periodosService;

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
    public ResponseEntity<PeriodoSol> periodosCheck() {  
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            // sino se indica que acepta el cliente, siempre devolveremos json.
            try {
                PeriodoSol detalle = periodosService.getPeriodos();
                ResponseEntity<PeriodoSol> respuesta = new ResponseEntity<>(detalle, HttpStatus.OK);
                return respuesta;
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                // solo para entregar mayor informacion devolveremos la excepcion
                throw e;
            }

        } else {
            log.warn(
                    "ObjectMapper or HttpServletRequest not configured in default DefaultApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
