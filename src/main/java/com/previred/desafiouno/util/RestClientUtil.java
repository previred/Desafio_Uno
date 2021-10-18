package com.previred.desafiouno.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.previred.desafiouno.dto.PeriodosDTO;
import com.previred.desafiouno.exception.RestClientException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestClientUtil {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String APPLICATION_JSON = "application/json";

    private RestClientUtil(){
    }

    public static PeriodosDTO getPeriodosFromGDD(String endpoint) throws RestClientException {

        HttpResponse<String> response;
        try {
            response = get(endpoint);
        }catch (UnirestException exception){
            throw new RestClientException("No se logro llamar al servivio GDD ["+endpoint+"]", exception);
        }

        if(HttpStatus.OK.value() != response.getStatus()){
            throw new RestClientException("El código de respuesta desde el servicio GDD no fue exitosa ["+response.getStatus()+"]");
        }
        try {
            JSONObject jsonObject = new JSONObject(response.getBody());
            List<String> fechas = new ArrayList<>();
            if (jsonObject.get("fechas") != null) {
                jsonObject.getJSONArray("fechas").forEach(jsonElement -> fechas.add((String) jsonElement));
            }

            PeriodosDTO periodosDTO = jsonToObject(response.getBody(), PeriodosDTO.class);
            periodosDTO.setFechas(fechas);
            return periodosDTO;
        }catch (Exception exception){
            throw new RestClientException("Hubo un problema al parsear el body de respuesta del servicio GDD ["+response.getBody()+"]", exception);
        }

    }

    private static HttpResponse<String> get(String endpoint) throws UnirestException {
        return Unirest.get(endpoint).
                header(HEADER_CONTENT_TYPE, APPLICATION_JSON).
                header(HEADER_ACCEPT, APPLICATION_JSON).
                asString();
    }
    public static <T> T jsonToObject(String jsonString, Class<T> objectClass) throws IOException {
        return (new ObjectMapper()).readValue(jsonString, objectClass);
    }
}
