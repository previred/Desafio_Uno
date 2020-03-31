package com.example.demo.client;

import com.example.demo.entity.Periodo;
import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class PeriodoClient implements IPeriodoClientService{

    private final RestTemplate restTemplate;

    public PeriodoClient(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }


    @Override
    public Periodo obtenerPeriodo() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList((MediaType.APPLICATION_JSON)));
        HttpEntity<String> request = new HttpEntity<>(headers);
        String url = "http://127.0.0.1:8080/periodos/api";

        ResponseEntity<String> response = this.restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class);
        Periodo periodo = new Gson().fromJson(response.getBody(), Periodo.class);
        return periodo;

    }
}
