package com.previred.lost.periods.services;

import com.previred.lost.periods.domain.dto.PeriodDTO;

/**
 * Facade that defines operations to call GDD Period REST service.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
public interface GDDPeriodService {

    PeriodDTO getRandomPeriods();

}
