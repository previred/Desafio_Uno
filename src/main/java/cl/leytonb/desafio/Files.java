package cl.leytonb.desafio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Files {

	public static void createFile(GeneradorResp generador, List<LocalDate> missingDates, String filename)
			throws FileNotFoundException, IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("fecha creacion  : ").append(generador.getFechaCreacion()).append('\n');
		sb.append("fecha fin       : ").append(generador.getFechaFin()).append('\n');
		sb.append("fechas recibidas: ").append(Arrays.toString(generador.getFechas().toArray())).append('\n');
		sb.append("fechas faltantes: ").append(Arrays.toString(missingDates.toArray())).append('\n');

		try (FileOutputStream fout = new FileOutputStream(filename)) {
			fout.write(sb.toString().getBytes());
		}
	}

}
