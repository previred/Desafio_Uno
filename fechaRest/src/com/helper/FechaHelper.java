package com.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.vo.FechasVO;

public class FechaHelper {

	
	private static final String UTF8 = "UTF-8";
	private static final String USER_AGENT = "Mozilla/5.0";

	public HttpURLConnection getConnection(String urlApi) throws Exception {
		try{			
			URL url = new URL(urlApi);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			return conn;
		}catch(Exception e){
			throw new Exception("Error obtener conexion ws: "+e.getMessage());
		}
	}

	public String getMessage(HttpURLConnection conn) throws Exception {
		
		BufferedReader br = null;
		StringBuilder sb = null;
		try{
			String line = null;
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(),UTF8));
			sb = new StringBuilder();
				while ((line = br.readLine()) != null) {
				    sb.append(line);
				}
		}catch(Exception e){
			throw new Exception("Error obtencion json respuesta: "+e.getMessage());
		}finally{
			br.close();
		}

		return sb.toString();
	}

	public FechasVO getObjFechas(String resp) throws Exception {
		FechasVO obj = new FechasVO();
		try{
			obj.setId(new JSONObject(resp).getInt("id"));
			obj.setFechaCreacion(new JSONObject(resp).getString("fechaCreacion"));
			obj.setFechaFin(new JSONObject(resp).getString("fechaFin"));
			obj.setFechas(getFechas(new JSONArray(new JSONObject(resp).getString("fechas"))));
		}catch(Exception e){
			throw new Exception("Error obtencion fechas: "+e.getMessage());
		}
		return obj;
	}

	private ArrayList<String> getFechas(JSONArray jsonArray) throws JSONException {
		ArrayList<String> listaFechas = new ArrayList<String>();
		for(int i = 0; i < jsonArray.length(); i++)
		{
			listaFechas.add(jsonArray.getString(i));
		}
		return listaFechas;
	}

	public ArrayList<String> getFechaSalida(FechasVO obj) throws Exception {		
		String fecha = obj.getFechaCreacion();
		ArrayList<String> lista = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = (Date) format.parse(fecha);
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);

//		while(!obj.getFechaFin().equalsIgnoreCase(fecha)){
		while(format.parse(fecha).compareTo(format.parse(obj.getFechaFin())) <=0){
			if(!obj.getFechas().contains(fecha)){
				if(lista.size() < 100){
					lista.add(fecha);
				}else{
					break;
				}				
			}
			cc.add(Calendar.MONTH, 1);
			fecha = format.format(cc.getTime());
			System.out.println(fecha);
		}
		
		return lista;
	}

	public void crearArchivo(String json,String nombre) {
		try {
            File file = new File(nombre);

            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.flush();
			writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}	
	
}
