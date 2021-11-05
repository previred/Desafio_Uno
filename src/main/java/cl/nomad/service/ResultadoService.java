package cl.nomad.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import cl.nomad.system.data.entity.Resultado;

public interface ResultadoService {

	String buscarDesafio() throws JsonProcessingException;

	Resultado buscarJson();

}
