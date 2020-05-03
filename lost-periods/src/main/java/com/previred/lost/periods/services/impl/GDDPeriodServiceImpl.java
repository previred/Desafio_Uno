package com.previred.lost.periods.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.lost.periods.domain.dto.PeriodDTO;
import com.previred.lost.periods.services.GDDPeriodService;
import com.previred.lost.periods.utils.StringFormatUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Implements service operations of facade {@link GDDPeriodService}. Define
 * business logic to call REST service "Generador Datos Desaf&iacute;o Uno"
 * (GDD). It class a represents Anticorruption Layer of GDD service.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
@Slf4j
@Service
public class GDDPeriodServiceImpl implements GDDPeriodService {

    @Value("${com.previred.lost.periods.generador_datos_desafio.service_protocol}")
    private String serviceProtocol;

    @Value("${com.previred.lost.periods.generador_datos_desafio.service_host}")
    private String serviceHost;

    @Value("${com.previred.lost.periods.generador_datos_desafio.service_port}")
    private String servicePort;

    @Value("${com.previred.lost.periods.generador_datos_desafio.context_path}")
    private String contextPath;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * Implements a client REST using class {@link RestTemplate} to call GDD service
     * to obtains a new set of periods between two random dates.
     * 
     * @return Object {@link PeriodDTO} that contains two different random dates and
     *         a set of dates between these first two dates.
     * @see PeriodDTO
     */
    @Override
    public PeriodDTO getRandomPeriods() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String serviceUrl = StringFormatUtils.getUrlService(serviceProtocol, serviceHost, servicePort, contextPath);

        log.info("Calling service '" + serviceUrl + "'");

        ResponseEntity<PeriodDTO> response = restTemplate.exchange(serviceUrl, HttpMethod.GET, entity,
                new ParameterizedTypeReference<PeriodDTO>() {
                });

        return response.getBody();
    }

}
