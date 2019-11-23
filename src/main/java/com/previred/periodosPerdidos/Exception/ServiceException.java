package com.previred.periodosPerdidos.Exception;

/**
 * Created by Matias Arce on 11/23/2019.
 */
public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
