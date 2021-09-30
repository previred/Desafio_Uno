package cl.previred.main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
*
* Autor: Mario Villalobos.
* Fecha: 30/09/2021
* Objetivo: Leer un archivo en formato JSon con fechas generadas dentro de un rango,
* para posteriormente generar otro archivo con el mismo formato agregando una coleccion 
* con las fechas faltantes dentro del rango.
*  
*/

import cl.previred.utils.MesesPorRango;

public class GenFechasFaltantes {
	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				System.out.println("Debe ingresar nombre del archivo de entrada y salida");
				return;
			}

			if (args.length== 1) {
				System.out.println("Debe ingresar nombre del archivo de salida");
				return;
			}

			// Se realiza la lectura del archivo y se almacena en la variable cadenaJson
			FileReader entrada = new FileReader(args[0]);
			BufferedReader bufferIn = new BufferedReader(entrada);

			String linea = "";
			String cadenaJSon = "";

			do {
				cadenaJSon += linea;
				linea = bufferIn.readLine();
			} while (linea != null);

			// Se deserializa la cadena Json a un objeto de la clase Periodos
			Gson gson = new Gson();
			Periodos periodos = gson.fromJson(cadenaJSon, Periodos.class);

			// Se obtiene la diferencia en meses entre la fecha de creacion y fecha fin
			MesesPorRango mesesPorRango = new MesesPorRango(periodos.getFechaCreacion(), periodos.getFechaFin());
			mesesPorRango.calcularDifMeses();
			int difMeses = mesesPorRango.getDifMeses();

			// Se obtienen el agno y mes de inicio del rango
			int agnoIni = mesesPorRango.getAgnoIni();
			int mesIni = mesesPorRango.getMesIni();
			int k = 0;
			String fechaPaso = "";

			//Se generan y almacenan en una tabla Hash el total de meses contenidos en el
			//rango
			LinkedHashMap<String, String> fecPeriodoHash = new LinkedHashMap<String, String>();
			Formatter formatNumber;

			while (k < difMeses) {
				formatNumber = new Formatter();
				fechaPaso = String.valueOf(agnoIni) + "-" + String.valueOf(formatNumber.format("%02d", mesIni)) + "-01";
				fecPeriodoHash.put(fechaPaso, fechaPaso);
				mesIni++;

				if (mesIni > 12) {
					mesIni = 1;
					agnoIni++;
				}
				k++;
			}

			// Posteriormente se recorren las fechas generadas y se buscan por
			// clave en la HashTable, si existe se borra de la tabla para que quedar solo con las fechas perdidas
			for (int i = 0; i < periodos.getFechas().length; i++) {
				if (fecPeriodoHash.containsValue(periodos.getFechas()[i])) {
					fecPeriodoHash.remove(periodos.getFechas()[i]);
				}

			}

			// Se asignan las fechas faltantes a la propiedad fechasFaltantes del objeto periodos
			String[] fechasFaltantes = new String[fecPeriodoHash.size()];
			int i = 0;

			for (Map.Entry<String, String> mapElement : fecPeriodoHash.entrySet()) {
				fechasFaltantes[i++] = mapElement.getValue();
			}

			periodos.setFechasFaltantes(fechasFaltantes);

			// Se formatea el contenido del objeto periodos
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String jsonFormateado = prettyGson.toJson(periodos);

			// Se almacena el resultado
			FileWriter salida = new FileWriter(args[1]);
			salida.write(jsonFormateado);
			System.out.println("El archivo "+args[1]+" ha sido creado con exito");

			entrada.close();
			salida.flush();
			salida.close();

		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + args[0] + " no fue encontrado\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
