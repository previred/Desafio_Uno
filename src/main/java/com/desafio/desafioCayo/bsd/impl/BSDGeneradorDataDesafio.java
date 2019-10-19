package com.desafio.desafioCayo.bsd.impl;

import com.desafio.desafioCayo.bsd.BSDGeneradorDataDesafioInterface;
import com.desafio.desafioCayo.client.GeneradorDataDesafio;
import com.desafio.desafioCayo.dto.FechasGeneradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BSDGeneradorDataDesafio implements BSDGeneradorDataDesafioInterface {

    @Autowired
    private GeneradorDataDesafio client;

    @Override
    public FechasGeneradorDTO generateRequest() {
        return client.getGeneradorDD();
    }
}
