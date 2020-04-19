package com.previred.desafio.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.previred.desafio.MissingDates;
import com.previred.desafio.models.InputModel;
import com.previred.desafio.models.OutputModel;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Collections;

@RestController
public class FaltantesController {

    private static final String url = "http://127.0.0.1:8080/periodos/api";

    @GetMapping("/faltantes")
    public ResponseEntity<?> faltantes() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<String> responseInput = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ResponseEntity<?> responseEntity = null;
        if (HttpStatus.OK.equals(responseInput.getStatusCode())) {
            try {
                OutputModel outputModel = MissingDates.find(gson.fromJson(responseInput.getBody(),InputModel.class));
                responseEntity = ResponseEntity.ok(gson.toJson(outputModel));
            }catch ( ParseException ex){
                responseEntity = new ResponseEntity<String>(
                        "Error parsing dates", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            responseEntity = new ResponseEntity<String>(
                    "Couldn't reach service at "+ url +"", responseInput.getStatusCode());
        }
        return responseEntity;
    }
}
