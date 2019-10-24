package com.beltran.anthony.challenge.api;

import com.beltran.anthony.challenge.model.Periodo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@io.swagger.annotations.Api(value = "api", description = "the api API")
public interface Api {

    Logger log = LoggerFactory.getLogger(Api.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    @RequestMapping(value = "/api",
            produces = {"application/json"},
            method = RequestMethod.GET)
    default ResponseEntity<Periodo> periodosFaltantes() {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{\"id\" : 0,  \"fechaCreacion\" : \"2019-10-24\", \"fechaFin\" : \"2019-10-24\",  \"fechas\" : [ \"2019-10-24\" ],  \"fechasFaltantes\" : [ null ],}", Periodo.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
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
