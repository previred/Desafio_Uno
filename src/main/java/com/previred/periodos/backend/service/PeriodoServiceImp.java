package com.previred.periodos.backend.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.previred.periodos.backend.util.Constante;
import com.previred.periodos.backend.util.Funcion;
import com.previred.periodos.model.Periodo;

/**
 *
 * @author arojas
 */
@Service
public class PeriodoServiceImp implements PeriodoService {

	final static Logger logger = Logger.getLogger(PeriodoServiceImp.class);
	final static Gson gson = new GsonBuilder().setDateFormat(Constante.FORMATO_FECHA_DEFECTO).create();

	@Autowired
	GeneradorDeDatoService gddService;

	@Override
	public Periodo getPeriodosFaltantes() {
		Periodo data = new Periodo();
		try {
			String json = gddService.getGDD();
			if (json != null) {
				Type collectionType = new TypeToken<Periodo>() {
				}.getType();
				data = (Periodo) gson.fromJson(json, collectionType);
				List<String> listaFaltante = Funcion.generarFechas(data.getFechaCreacion(), data.getFechaFin(),
						data.getFechas());
				if (listaFaltante.size() > 0)
					data.setFechasFaltantes(listaFaltante.stream().sorted().collect(Collectors.toList()));
			}
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
		}
		return data;
	}
}
