package com.previred.lost.periods.domain.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents status information of a Lost Periods API call result.<br/>
 * <ul>
 * <li>{@link ResponseStatus#code}: HTTP code of the result (500, 200, 401,
 * etc.).</li>
 * <li>{@link ResponseStatus#type}: Description of HTTP code ("OK",
 * "Unauthorized", etc.).</li>
 * <li>{@link ResponseStatus#message}: Description of error cause, it could be a
 * custom message or the {@link Exception} message.</li>
 * <li>{@link ResponseStatus#timestamp}: Timestamp of service execution in ISO
 * DATE format.</li>
 * </ul>
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseStatus implements Serializable {

    private static final long serialVersionUID = -5834940611500322036L;

    private int code;
    private String type;
    private String message;
    private String timestamp;

}
