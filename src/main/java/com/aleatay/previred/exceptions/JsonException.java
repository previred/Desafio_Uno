package com.aleatay.previred.exceptions;

/**
 * @author Alexis Rivas
 * @date 03/09/2020
 **/

public class JsonException extends Exception {

    public JsonException(String message){
        super(message);
    }

    public JsonException(String message, Throwable e){
        super(message, e);
    }
}
