package com.app;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.helper.FechaHelper;
import com.vo.FechasVO;



@Path("/fechaRest")
@Consumes(MediaType.APPLICATION_JSON)
public class FechaRest {

	private static final String erroJson = "{\"CodError\":1,\"mensaje\":\"Error obteniendo Fechas\",\"status\": 400}";
	private static final String url = "http://127.0.0.1:8080/periodos/api";
	private FechaHelper helper = new FechaHelper();
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getFechas(){
		
		HttpURLConnection conn = null;
		String response = null;
		FechasVO obj = null;
		ArrayList<String> listaFechasSalida = null;
		String json = null;
		try{
			conn = helper.getConnection(url);
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
				response = helper.getMessage(conn);
				obj = helper.getObjFechas(response);
				listaFechasSalida = helper.getFechaSalida(obj);
				obj.setFechasFaltantes(listaFechasSalida);
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				json = ow.writeValueAsString(obj);
				System.out.println(json);
			}else{
				json = erroJson;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			json = erroJson;
		}finally {
			if(null != response) helper.crearArchivo(response,"request.json");
			if(null != json) helper.crearArchivo(json,"response.json");
		}	
		return json;
	}	
}
