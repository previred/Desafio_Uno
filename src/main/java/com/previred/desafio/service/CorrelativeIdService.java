package com.previred.desafio.service;

import org.springframework.stereotype.Service;

/**
 * service for controller the correlative id
 */
@Service
public class CorrelativeIdService {

    /**
     * the id
     */
    private Integer id = 0;

    /**
     * get and increase id
     *
     * @return the id
     */
    public Integer getId() {
        id++;
        return id;
    }


}
