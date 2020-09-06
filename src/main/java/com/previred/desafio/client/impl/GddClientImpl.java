package com.previred.desafio.client.impl;

import com.previred.desafio.client.GddClient;
import com.previred.desafio.dto.GddDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class GddClientImpl implements GddClient {

    @Value("${url.gddServer}")
    protected String gddServer;
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public GddDto getDataDesafio() {
        try {
            log.info("[getDataDesafio] [INI_OK]");
            URI uri = new URI(this.gddServer+"periodos/api");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");

            HttpEntity entity = new HttpEntity(headers);
            return restTemplate.exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<GddDto>() {
            }).getBody();

        } catch (Exception e) {
            log.error("[getDataDesafio]" , e);
            return null;
        }
    }
}
