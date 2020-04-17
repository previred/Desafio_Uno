package com.example.demoFechas;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

@SpringBootApplication
public class DemoFechasApplication {


	private static ArrayList<LocalDate> periodosFaltantes;
	private static ArrayList<LocalDate> periodosEntrada = new ArrayList<LocalDate>();

	public static void main(String[] args) {
		SpringApplication.run(DemoFechasApplication.class, args);
		String ruta_entrada = args[0];
		String ruta_salida = args[1];
		load( ruta_entrada, ruta_salida);
	}


	private static void load(String ruta_entrada, String ruta_salida) {


		JSONParser parser = new JSONParser();
		try {
			/* el primer argumento del llamado es la ruta al json */
			Object obj = parser.parse(new FileReader(ruta_entrada));
			JSONObject jsonObject = (JSONObject) obj;
			LocalDate fechaCreacion = LocalDate.parse((String) jsonObject.get("fechaCreacion"));
			LocalDate fechaFin = LocalDate.parse((String) jsonObject.get("fechaFin"));
			Object id = jsonObject.get("id");

			PeriodosPerdidos(fechaCreacion,fechaFin);
			generaJson(fechaCreacion, fechaFin, id, ruta_salida);
			Iterator<LocalDate> l = periodosFaltantes.iterator();
			/* while (l.hasNext())
				System.out.println(l.next());*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/* Busca un periodo dentro del array de fechas de entrada retornando true si la ubica */
	private static boolean buscar(LocalDate periodo)
	{
		for (LocalDate d: periodosEntrada) {
			if (periodo.isEqual(d))
				return true;
		}
		return false;
	}
	
	/* Agrega un periodo al atributo periodosFaltantes  */
	private  static void addPeriodo(LocalDate periodo) {
	    if (periodosFaltantes == null) {
	      periodosFaltantes = new ArrayList<LocalDate>();
	    }
	    periodosFaltantes.add(periodo);
	   }
	
	private static void generaJson(LocalDate fechaCreacion, LocalDate fechaFin, Object id, String ruta_salida) {

		JsonObject miObjeto = new JsonObject();
		Gson miGson = new Gson();
		miObjeto.addProperty("id", (Number) id);
		miObjeto.addProperty("fechaCreacion", fechaCreacion.toString());
		miObjeto.addProperty("fechaFin", fechaFin.toString());
		miObjeto.addProperty("fechasFaltantes", String.valueOf(periodosFaltantes));
		System.out.print(miObjeto);
		try (Writer writer = new FileWriter(ruta_salida)) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(miObjeto, writer);
		}catch (Exception e){
			e.printStackTrace();
		}



	}
	
	/* Determina los periodos faltantes*/
	private static void PeriodosPerdidos(LocalDate periodo, LocalDate fFin) {
	int i = -1;
		do {
		i += 1;
		if (!buscar(periodo.plusMonths(i)))
		addPeriodo(periodo.plusMonths(i));
		
		}	while (periodo.plusMonths(i).isBefore(fFin));
	}
	
	


}
