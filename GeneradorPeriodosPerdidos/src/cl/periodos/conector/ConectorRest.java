package cl.periodos.conector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import cl.periodos.model.PeriodoTO;

import com.google.gson.Gson;

public class ConectorRest {

	private static final int STATUSOK = 200;

	
	/**
	 * Método encargado de obtener periodos desde servicio Rest.
	 * @return PeriodoTO Objeto con los datos obtenidos desde servicio Rest.
	 */
	public PeriodoTO obtenerFechas() {
			
		String respuesta = "";
		PeriodoTO periodos = new PeriodoTO(); 
			
		try {
			
			String urlRest = obtenerUrlRest();
			
			if(urlRest == null || urlRest.equals("")){
				urlRest = "http://127.0.0.1:8080/periodos/api";
			}
			
			URL url = new URL(urlRest);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != STATUSOK) {
				throw new RuntimeException("[CambioEstado][cambiarEstadoOperacional]Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			Scanner br = new Scanner(new InputStreamReader((conn.getInputStream())));
			while (br.hasNextLine()) {
				respuesta += br.nextLine();
			}
			br.close();
			conn.disconnect();

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
		
		if(respuesta != null){
			periodos = new Gson().fromJson(respuesta, PeriodoTO.class);
		}
		
		return periodos;
	}

	/**
	 * Metodo que obtiene valor desde archivo .properties.
	 * @return
	 */
	private String obtenerUrlRest() {
		Properties prop = new Properties();
		String url = "";
		
	       try {
	    	   prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
	        } catch (IOException e) {
	        	System.out.println("[obtenerUrlRest] Ocurrio un error al obtener url");
	        	e.getMessage();
	        }
		
	       url = prop.getProperty("URL");

		return url;
	}	
}
