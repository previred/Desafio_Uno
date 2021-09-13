package cl.previred.desafiouno.swagger.codegen.api;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import cl.previred.desafiouno.servicio.DateProcessorService;
import cl.previred.desafiouno.swagger.codegen.model.MissingDateRequest;
import cl.previred.desafiouno.swagger.codegen.model.MissingDateResponse;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Optional;

@Controller
public class ApiApiController implements ApiApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private DateProcessorService dateProcessorService;

    @Value( "${application.gddurl}" )
    private String gddUrl;    

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
    public ResponseEntity<MissingDateResponse> periodosfaltantes(@Valid @RequestBody MissingDateRequest fechas) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    MissingDateResponse missingDates = dateProcessorService.getMissingDates(fechas);
                    ResponseEntity<MissingDateResponse> response = new ResponseEntity<>(missingDates, HttpStatus.OK);
                    return response;
                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ApiApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }


    public ResponseEntity<MissingDateResponse> periodosfaltantesGdd() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    //llama a gdd
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<MissingDateRequest> responsegdd  = restTemplate.getForEntity(gddUrl, MissingDateRequest.class);
                    //procesa el resultado
                    return periodosfaltantes(responsegdd.getBody());

                } catch (Exception e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default ApiApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
