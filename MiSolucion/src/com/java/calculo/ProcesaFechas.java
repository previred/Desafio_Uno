package com.java.calculo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import com.java.dto.FechasDTO;

public class ProcesaFechas {

	public FechasDTO procesa(FechasDTO fechasEntrantesDTO) {
		
		FechasDTO fechasSalientesDTO = new FechasDTO();

		try {

			ArrayList fechasSalientesArray = new ArrayList();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			LocalDate fechaInicio = LocalDate.parse(fechasEntrantesDTO.getFechaInicio(), formatter);
			LocalDate fechaFin = LocalDate.parse(fechasEntrantesDTO.getFechaFin(), formatter);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

			// buscar si viene la fecha de inicio y la fecha de fin en la lista
			while (fechaInicio.isBefore(fechaFin)) {

				fechasSalientesArray.add(fechaInicio);
				fechaInicio = fechaInicio.plusMonths(1);

			}
			fechasSalientesArray.add(fechaFin);

			fechasSalientesDTO.setId(fechasEntrantesDTO.getId());
			fechasSalientesDTO.setFechaInicio(fechasEntrantesDTO.getFechaInicio());
			fechasSalientesDTO.setFechaFin(fechasEntrantesDTO.getFechaFin());

			for (int i = 0; i < fechasEntrantesDTO.getFechas().size(); i++) {
				for (int j = 0; j < fechasSalientesArray.size(); j++) {
					if (fechasEntrantesDTO.getFechas().get(i).equals(fechasSalientesArray.get(j).toString())) {
						fechasSalientesArray.remove(j);
					}
				}
			}

			for (int j = 0; j < fechasSalientesArray.size(); j++) {
				System.out.println(fechasSalientesArray.get(j));
			}

			fechasSalientesDTO.setFechaInicio(fechasEntrantesDTO.getFechaInicio());
			fechasSalientesDTO.setFechaFin(fechasEntrantesDTO.getFechaFin());
			fechasSalientesDTO.setId(fechasEntrantesDTO.getId());
			fechasSalientesDTO.setFechasSalientes(fechasSalientesArray);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechasSalientesDTO;
	}

}
