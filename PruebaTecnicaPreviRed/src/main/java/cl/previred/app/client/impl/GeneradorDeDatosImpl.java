package cl.previred.app.client.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cl.previred.app.client.IGeneradorDeDatos;

@Component
public class GeneradorDeDatosImpl implements IGeneradorDeDatos {
	private static final Logger logger = LoggerFactory.getLogger(GeneradorDeDatosImpl.class);

	
	@Value("${metodo.api.fechas}")
	public String metodo;
	
	@Value("${heards.api.accept.key}")
	public String key;
	
	@Value("${heards.api.accept.value}")
	public String value;
	
	@Value("${error.conectividad}")
	public String error;
	
	
	
	/**
	 *  Método que conecta con la API externa para obtener la información de las fechas
	 * @param url
	 * @return string
	 * @exception Exception
	 */
	@Override
	public String getDataApi(String url) throws Exception, RuntimeException, ConnectException{
		try {
			URL urlFinal = new URL(url);
		    HttpURLConnection conn = (HttpURLConnection) urlFinal.openConnection();
		    conn.setRequestMethod(metodo);
		    conn.setRequestProperty(key, value);
		    String json = "";
		    if (conn.getResponseCode() != 200) 
		        throw new RuntimeException(error.concat(" ").concat(String.valueOf(conn.getResponseCode())));
		    
		    InputStreamReader in = new InputStreamReader(conn.getInputStream());
		    BufferedReader br = new BufferedReader(in);
		    String output;
		    while ((output = br.readLine()) != null) {
		    	json = json + output;
		    }
		    conn.disconnect();
		    return json;
		}catch (RuntimeException | ConnectException e) {
			logger.warn("Error en el componente de conección a la api externa", e);
			throw e;
		}catch (Exception e) {
			logger.error("Error inesperado por la api", e);
			throw e;
		}
		
	}



	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}



	public void setKey(String key) {
		this.key = key;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public void setError(String error) {
		this.error = error;
	}
	
	
	
	

}
