package com.previred.periodos.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.previred.periodos.model.Periodo;
import com.previred.periodos.model.PeriodoFechasFaltantes;
import com.previred.periodos.servicio.PeriodosService;
import com.previred.periodos.util.MyException;

/**
 *
 * @author Nelson Villamizar
 */
public class FechasFaltantesController {

	private PeriodosService periodosService;
	StringBuilder sbLogWhenSalidaFile = new StringBuilder();

	FechasFaltantesController(PeriodosService periodosService) {
		this.periodosService = periodosService;
	}

	private boolean validarArgs(String[] args) throws MyException {

		if (args.length == 0) {
			throw new MyException(
					"La ejecucion del programa debe incluir al menos un parametro; asi: $ nombreprograma nombre_archivo_entrada\n"
							+ "Ejemplo: nombreprograma entrada.json \n");
		}

		return true;

	}

	public void guardarMensaje(String message) {
		sbLogWhenSalidaFile.append(message).append("\n");
	}

	public void printMessageConsole(String message) {

		System.out.println(message);

	}

	public void procesar(String[] args) {

		try {
			validarArgs(args);
		} catch (MyException e1) {
			printMessageConsole(e1.getMessage());
			System.exit(1);

		}
		String nombreArchivoEntrada = null;
		if (args.length >= 1)
			nombreArchivoEntrada = args[0];

		String nombreArchivoSalida = null;
		if (args.length >= 2)
			nombreArchivoSalida = args[1];

		guardarMensaje("Archivo de entrada:" + nombreArchivoEntrada);
		guardarMensaje("Archivo de salida:" + nombreArchivoSalida);

		Periodo PeriodoArchivoEntrada = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		try {
			PeriodoArchivoEntrada = mapper.readValue(new File(nombreArchivoEntrada), Periodo.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (JsonMappingException e) {

			printMessageConsole("Verifique que el archivo de Entrada tiene la estructura valida de un Archivo Json :"
					+ nombreArchivoEntrada);
			printMessageConsole("Sale este error:" + e.getMessage());
			System.exit(1);

		} catch (IOException e) {
			printMessageConsole(
					"Verifique que el archivo de Entrada existe y  tiene la estructura valida de un Archivo Json:"
							+ nombreArchivoEntrada);
			printMessageConsole("Sale este error:" + e.getMessage());
			System.exit(1);
		}

		guardarMensaje("Archivo de entrada leido correctamente");

		PeriodoFechasFaltantes periodoFechasFaltantes = null;

		try {
			periodoFechasFaltantes = periodosService.getFechasFaltantes(PeriodoArchivoEntrada);
		} catch (MyException e1) {
			printMessageConsole(e1.getMessage());
			System.exit(1);

		}

		guardarMensaje("Se identificaron las fechas faltantes");

		String indented = null;
		try {
			String jsonString = mapper.writeValueAsString(periodoFechasFaltantes);

			PeriodoFechasFaltantes periodox = mapper.readValue(jsonString, PeriodoFechasFaltantes.class);

			indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(periodox);
			indented = indentarManual(indented);

			

		} catch (IOException e1) {
			printMessageConsole("Error generando la salida:" + e1.getMessage());
			System.exit(1);
		}

		if (nombreArchivoSalida != null) {
			try (FileWriter file = new FileWriter(nombreArchivoSalida)) {

				file.write(indented);
				file.flush();

			} catch (IOException e) {
				printMessageConsole("Error escribiendo en el archivo de  salida:" + e.getMessage());
				System.exit(1);
			}

			guardarMensaje("El archivo de salida fue generado correctamente.");
		}
		
		if (nombreArchivoSalida == null) {
			printMessageConsole(indented);
		} else {

			printMessageConsole(sbLogWhenSalidaFile.toString());
			printMessageConsole(indented);

		}
	}

	/****
	 * Dado que la lista se retorna sin linea feed se hacen reemplazos para
	 * lograr una salida similar a la que retorna el servicio
	 * 
	 * @param indented
	 * @return
	 */
	private String indentarManual(String indented) {

		indented = indented.replace("\" : [ \"", "\" : [ \n    \"").replace("\" ]", "\"\n ]").replace("\" : ", "\": ")
				.replace("\", \"", "\",\n    \"");

		return indented;

	}

}
