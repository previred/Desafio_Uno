package com.java.main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.java.calculo.ProcesaFechas;
import com.java.dto.FechasDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class LeerEscribirArchivo {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		FechasDTO fechasSalientesDTO = new FechasDTO();
		JSONParser parser = new JSONParser();
		FechasDTO fechasDTO = new FechasDTO();

		String nombreArchivoSalida = args[1];
		try {
			Object obj = parser.parse(new FileReader(args[0]));
			JSONObject jsonObject = (JSONObject) obj;

			fechasDTO.setId(String.valueOf(jsonObject.get("id")));
			fechasDTO.setFechaInicio(String.valueOf(jsonObject.get("fechaCreacion")));
			fechasDTO.setFechaFin(String.valueOf(jsonObject.get("fechaFin")));

			// recorrer arreglo de fechas
			JSONArray leng = (JSONArray) jsonObject.get("fechas");
			Iterator iterator = leng.iterator();
			ArrayList<String> arregloFechas = new ArrayList<String>();

			while (iterator.hasNext()) {
				arregloFechas.add(iterator.next().toString());
			}
			fechasDTO.setFechas(arregloFechas);

			ProcesaFechas procesaFechas = new ProcesaFechas();
			fechasSalientesDTO = procesaFechas.procesa(fechasDTO);
			escribeArchivo(fechasSalientesDTO, nombreArchivoSalida);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}



	public static void escribeArchivo(FechasDTO fechasSalientesDTO, String nombreArchivoSalida) {
		
		JSONObject obj = new JSONObject();
		JSONArray list = new JSONArray();

		obj.put("id", fechasSalientesDTO.getId());
		obj.put("fechaCreacion", fechasSalientesDTO.getFechaInicio());
		obj.put("fechaFin", fechasSalientesDTO.getFechaFin());

		for(int i = 0; i < fechasSalientesDTO.getFechasSalientes().size();i++) {
			list.add(fechasSalientesDTO.getFechasSalientes().get(i));

		}
		obj.put("fechasFaltantes", list);

		try {
			FileWriter file = new FileWriter(nombreArchivoSalida);
			file.write(obj.toJSONString());
			file.flush();
			file.close();

		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		} finally {
			System.out.print(obj);
		}
	}

}
