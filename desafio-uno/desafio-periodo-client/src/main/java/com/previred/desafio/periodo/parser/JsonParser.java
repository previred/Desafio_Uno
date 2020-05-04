package com.previred.desafio.periodo.parser;

import com.previred.desafio.periodo.dto.RespuestaPeriodo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Clase para Parsear de json a Objecto
 * @author Roderick Rangel
 */
public class JsonParser {
    
    /**
     * Arma el Objeto respuesta a partir de un json
     * @param response Json respuesta de GDD
     * @return Objeto respuesta
     */
    public static RespuestaPeriodo getRespuestaFecha(String response)
    {
        RespuestaPeriodo resultado = null;
        JSONObject jsonObject = new JSONObject(response);
        
        if(jsonObject.has("id"))
        {
            resultado = new RespuestaPeriodo(200, "OK");
            
            resultado.setFechaCreacion((String)jsonObject.get("fechaCreacion"));
            resultado.setFechaFin((String)jsonObject.get("fechaFin"));
            
            JSONArray jsonFechas = jsonObject.getJSONArray("fechas");
            
            resultado.setFechas(JsonParser.getListaFechas(jsonFechas));
        } 
        else
        {
            resultado = new RespuestaPeriodo(500, "Sin datos");
        }
       
        return resultado;
    }
    
    /**
     * Arma una lista a partir de un arreglo json
     * @param jsonFechas Arreglo json
     * @return Lista de fechas
     */
    private static List<String> getListaFechas(JSONArray jsonFechas)
    {
        List<String> lista = new ArrayList();
        
        for(int i = 0; i < jsonFechas.length(); i++)
        {
            lista.add((String)jsonFechas.get(i));
        }
        return lista;
    }
    
    
    
}
