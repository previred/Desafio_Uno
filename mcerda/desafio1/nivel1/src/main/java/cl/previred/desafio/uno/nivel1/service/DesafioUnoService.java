package cl.previred.desafio.uno.nivel1.service;

import java.util.List;

import cl.previred.desafio.uno.nivel1.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel1.exceptions.DesafioUnoInformedException;

public interface DesafioUnoService {

	public List<String> getFechasFaltantes(DesafioUnoFechasDTO fechas);

	public void print(String pathFile, DesafioUnoFechasDTO fechas) throws DesafioUnoInformedException;
}
