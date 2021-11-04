package com.cbravo;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class Consumidor {

	public static void main(String[] args) {
		Entrada ent = consumoGdd();
		Respuesta resp = buscarFechas(ent);
		crearArchivoResp(resp);
	}

	private static void crearArchivoResp(Respuesta resp) {
		try {

			Gson v_gson = new Gson();
			PrintWriter writer = new PrintWriter("./FechasFaltantes.txt", "UTF-8");
            writer.println("fecha creacion: "+ resp.getFechaCreacion());
            writer.println("fecha fin: "+ resp.getFechaFin());
            writer.println("fechas recibidas: "+ v_gson.toJson(resp.getFechas()));
            writer.println("fechas faltantes: "+ v_gson.toJson(resp.getFechasFaltantes()));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	private static Respuesta buscarFechas(Entrada in) {
		Date vfin = fechaToDate(in.getFechaFin());
		List<String> fvacias = new ArrayList<String>();
		List<String> lFechas = Arrays.asList(in.getFechas());
		for (Date vini = fechaToDate(in.getFechaCreacion()); !vini.after(vfin); vini=sumarMes(vini)) {
			if ( !lFechas.contains(fechaConFormato(vini)) ) {
				fvacias.add(fechaConFormato(vini));
			} 
			
		}
		Respuesta vc = new Respuesta(in, fvacias.toArray(new String[fvacias.size()]));
		return vc;
	}

	private static Entrada consumoGdd() {
		Entrada v_in = null;
		Gson v_gson = new Gson();
		String urlApi="http://localhost:8080/periodos/api";
        try{
            URL url = new  URL(urlApi);
            HttpURLConnection conexion=(HttpURLConnection)url.openConnection();
            conexion.setRequestProperty("Accept", "application/json");
            conexion.setRequestMethod("GET");
            conexion.connect();
            Scanner reader = new Scanner(new InputStreamReader(conexion.getInputStream(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			while (reader.hasNextLine()) {
				buffer.append(reader.nextLine());
			}
			v_in = v_gson.fromJson(buffer.toString(), Entrada.class);
            
        }catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
		return v_in;
	}
	public static Date sumarMes(Date vd) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(vd);
	    calendar.add(Calendar.MONTH, 1);
	    return calendar.getTime();
	}
	
	public static String fechaConFormato(Date fi) {
		String resp="";
		resp = new SimpleDateFormat("yyyy-MM-dd").format(fi);
		return resp;
	}
	
	public static Date fechaToDate(String vfecha) {
		Date f_d =null;
		try {
			f_d =  new SimpleDateFormat("yyyy-MM-dd").parse(vfecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return f_d;
	}

}
