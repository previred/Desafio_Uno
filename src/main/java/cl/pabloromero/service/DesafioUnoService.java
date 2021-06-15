package cl.pabloromero.service;

import org.springframework.stereotype.Service;

import cl.pabloromero.model.FechasFaltantesResponse;

@Service
public interface DesafioUnoService {

	public FechasFaltantesResponse getFechasFaltantes();
}
