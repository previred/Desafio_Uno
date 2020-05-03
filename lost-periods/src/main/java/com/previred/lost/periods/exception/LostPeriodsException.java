package com.previred.lost.periods.exception;

import com.previred.lost.periods.services.impl.LostPeriodsServiceImpl;

import lombok.Getter;

/**
 * Custom wrapper Exception to throw any error occurs inside
 * {@link LostPeriodsServiceImpl} class.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
@Getter
public class LostPeriodsException extends RuntimeException {

    private static final long serialVersionUID = 3896333167979053785L;

    private final String message;

    public LostPeriodsException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}
