package com.previred.desafio.periodo.rest.parser;

import com.previred.desafio.periodo.bean.Periodo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parser para pasar de Object a json
 * @author Roderick Rangel
 */
public class JsonParser {
    
   public static JSONObject getJsonRespuesta(Periodo periodoConsulta, List<String> fechasFaltantes)
   {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", periodoConsulta.getId());
        jsonObject.put("fechaCreacion", periodoConsulta.getFechaCreacion());
        jsonObject.put("fechaFin", periodoConsulta.getFechaFin());
        
        JSONArray jsonFechas = new JSONArray();
              
        for(int i=0; i < periodoConsulta.getFechas().size(); i++) 
        {
            jsonFechas.put(periodoConsulta.getFechas().get(i));
        }
        
        jsonObject.put("fechas", jsonFechas);
           
        JSONArray jsonFechasFaltantes = new JSONArray();
        
        for(int j=0; j < fechasFaltantes.size(); j++) 
        {
            jsonFechasFaltantes.put(fechasFaltantes.get(j));
            System.out.println("fechasFaltantes: " + fechasFaltantes.get(j));
        }
        
        jsonObject.put("fechasFaltantes", jsonFechasFaltantes);
        
       return jsonObject;
   }
}
