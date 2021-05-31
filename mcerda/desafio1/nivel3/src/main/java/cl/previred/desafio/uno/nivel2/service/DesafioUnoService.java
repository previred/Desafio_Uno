package cl.previred.desafio.uno.nivel2.service;

import cl.previred.desafio.uno.nivel2.dto.DesafioUnoFechasDTO;
import cl.previred.desafio.uno.nivel2.exceptions.DesafioUnoInformedException;

public interface DesafioUnoService {


	public void print(DesafioUnoFechasDTO fechas) throws DesafioUnoInformedException;
}
