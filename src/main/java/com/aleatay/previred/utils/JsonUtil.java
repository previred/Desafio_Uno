package com.aleatay.previred.utils;

import com.aleatay.previred.exceptions.JsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 */
public class JsonUtil {

    public static <T> T getObjectByJsonString(String json, Class<T> type) throws JsonException {

        try {
            return (T) new ObjectMapper().readValue(json, Class.forName(type.getName()));
        } catch (JsonProcessingException | ClassNotFoundException e) {
            throw new JsonException("Ocurrio un error al momento de extraer el Objeto del Json: " + json, e);
        }
    }

}
