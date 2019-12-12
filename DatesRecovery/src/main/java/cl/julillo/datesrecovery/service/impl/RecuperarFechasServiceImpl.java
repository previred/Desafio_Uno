package cl.julillo.datesrecovery.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import cl.julillo.datesrecovery.externalresources.feignclients.iGDDClient;
import cl.julillo.datesrecovery.model.dto.PeriodoDTO;
import cl.julillo.datesrecovery.service.iRecuperarFechasService;

@Service
public class RecuperarFechasServiceImpl implements iRecuperarFechasService {

	@Autowired
	private iGDDClient gddCli;

	@Override
	public PeriodoDTO recuperarFechas() {
		return this.gddCli.getPeriodos(Collections.singletonMap(HttpHeaders.ACCEPT, MediaType.toString(Collections.singletonList(MediaType.APPLICATION_JSON))));
	}

}
