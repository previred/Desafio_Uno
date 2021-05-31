package cl.previred.desafio.uno.nivel3.api.service;

import java.util.List;

import cl.previred.desafio.uno.nivel3.api.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel3.api.exceptions.DesafioUnoInformedException;

public interface DesafioUnoService {

	public List<String> getFechasFaltantes(DesafioUnoFechasDTO fechas);

	public void print(DesafioUnoFechasDTO fechas) throws DesafioUnoInformedException;
}
