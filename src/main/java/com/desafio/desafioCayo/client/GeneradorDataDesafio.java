package com.desafio.desafioCayo.client;

import com.desafio.desafioCayo.dto.FechasGeneradorDTO;
import com.desafio.desafioCayo.utils.Constantes;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class GeneradorDataDesafio {

    private static final Logger LOG = LoggerFactory.getLogger(GeneradorDataDesafio.class);
    Gson gson;

    public FechasGeneradorDTO getGeneradorDD() {
        RestTemplate restTemplate = new RestTemplate();

        String urlString = Constantes.BASE_URL;
        String salida;
        FechasGeneradorDTO respuesta = new FechasGeneradorDTO();
        try {
            salida = restTemplate.getForObject(Constantes.BASE_URL, String.class);
            Gson gson = new Gson();
            respuesta = gson.fromJson(salida, FechasGeneradorDTO.class);
        } catch (HttpClientErrorException e) {
            LOG.error("Error al realizar request metodo GDD: " + e.getMessage());
        }

        return respuesta;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
