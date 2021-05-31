package cl.previred.desafio.uno.nivel2.service;

import java.util.List;

import cl.previred.desafio.uno.nivel2.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel2.exceptions.DesafioUnoInformedException;

public interface DesafioUnoService {

	public List<String> getFechasFaltantes(DesafioUnoFechasDTO fechas);

	public void print(DesafioUnoFechasDTO fechas) throws DesafioUnoInformedException;
}
