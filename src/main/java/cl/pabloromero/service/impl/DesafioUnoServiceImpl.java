package cl.pabloromero.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.pabloromero.gateway.DesafioUnoGDDGateway;
import cl.pabloromero.model.FechasFaltantesResponse;
import cl.pabloromero.model.Periodos;
import cl.pabloromero.service.DesafioUnoService;

@Service
public class DesafioUnoServiceImpl implements DesafioUnoService {

	@Autowired
	private DesafioUnoGDDGateway gddGateway;
	
	public FechasFaltantesResponse getFechasFaltantes() {
		Periodos periodos = gddGateway.periodos();
		List<Date> fechasFaltantes =  new ArrayList<Date>();
		fechasFaltantes.add(new Date());
		return FechasFaltantesResponse.builder()
				.id(periodos.getId())
				.fechaCreacion(periodos.getFechaCreacion())
				.fechaFin(periodos.getFechaFin())
				.fechas(periodos.getFechas())
				.fechasFaltantes(fechasFaltantes)
				.build();
	}

}
