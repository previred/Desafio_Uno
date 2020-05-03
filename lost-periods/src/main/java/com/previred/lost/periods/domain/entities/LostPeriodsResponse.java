package com.previred.lost.periods.domain.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity that represents a response of Lost Period REST service. Contains a
 * {@link LostPeriod} object to return calculated lost periods information and a
 * {@link ResponseStatus} object to encapsulate the operation result status.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LostPeriodsResponse implements Serializable {

    private static final long serialVersionUID = -1510642648470279348L;

    private LostPeriods lostPeriods;
    private ResponseStatus status;
}
