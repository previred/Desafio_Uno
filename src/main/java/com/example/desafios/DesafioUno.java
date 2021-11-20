package com.example.desafios;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vo.Fechas;

@SpringBootApplication
public class DesafioUno {

	private static FileWriter file;

	public static void main(String[] args) {
		SpringApplication.run(DesafioUno.class, args);

		//inicializacion de objetos
		Fechas jsonFechasEntrada = new Fechas();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<String> fechasFaltantes = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		
		try {
			
			//Se obtienen argumentos de entrada y salida
			Object entrada = parser.parse(new FileReader(args[0]));
			String salida = args[1];
			
			//Se guarda el array de fechas en una Lista de Strings
			JSONObject jsonEntrada = (JSONObject) entrada;
			JSONArray fechas = (JSONArray) jsonEntrada.get("fechas");
			Iterator<String> fecha = fechas.iterator();

			//Se guardan todos los datos del archivo json de entradas en un objeto tipo Fechas
			jsonFechasEntrada.setId((Long) jsonEntrada.get("id"));
			jsonFechasEntrada.setFechaCreacion((String) jsonEntrada.get("fechaCreacion"));
			jsonFechasEntrada.setFechaFin((String) jsonEntrada.get("fechaFin"));
			while (fecha.hasNext()) {
				jsonFechasEntrada.getFechas().add(fecha.next());
			}

			//Se calcula la cantidad de meses que faltan entre la fecha de creacion y fin
			LocalDate creacion = LocalDate.parse(jsonFechasEntrada.getFechaCreacion(), formato);
			LocalDate fin = LocalDate.parse(jsonFechasEntrada.getFechaFin(), formato);

			while (creacion.isBefore(fin)) {
				creacion = creacion.plusMonths(1);
				String formattedString = creacion.format(formato);
				fechasFaltantes.add(formattedString);
			}
			

			//se eliminan las fechas inicialmente obtenidas en el json de entrada
			fechasFaltantes.removeAll(jsonFechasEntrada.getFechas());
			//llamado a metodo para escribir el archivo de salida
			escribirSalida(jsonFechasEntrada, fechasFaltantes, salida);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que escribe la salida del programa
	 * 
	 * @param jsonFechasEntrada
	 * @param fechasFaltanttes
	 */
	private static void escribirSalida(Fechas jsonFechasEntrada, List<String> fechasFaltantes, String salida) {
		JSONObject obj = new JSONObject();
		obj.put("fechaCreacion", jsonFechasEntrada.getFechaCreacion());
		obj.put("id", jsonFechasEntrada.getId());
		obj.put("fechaFin", jsonFechasEntrada.getFechaFin());
		JSONArray fechasArray = new JSONArray();
		
		if(fechasFaltantes.size()==0) { //si no hay fechas faltantes, de todas formas crea el key FechasFaltantes para cumplir el formato.
			fechasArray.add("");
			obj.put("FechasFaltantes", fechasArray);
		} else {
			for (int i = 0; i < fechasFaltantes.size(); i++) {
				if(i==100) { //si las fechas faltantes son mayores a 100 rompe el ciclo y solo devuelve 100 fechas
					break;					
				}
				String fecha = fechasFaltantes.get(i);
				fechasArray.add(fecha);
				obj.put("FechasFaltantes", fechasArray);
			}
		}
		try {

			file = new FileWriter(salida);
			file.write(obj.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			try {
				file.flush();
				file.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
}
