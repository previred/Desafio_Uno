package com.previred.lost.periods.services;

import com.previred.lost.periods.domain.dto.PeriodDTO;
import com.previred.lost.periods.domain.entities.LostPeriods;
import com.previred.lost.periods.exception.LostPeriodsException;

/**
 * Facade that defines operations to manage lost periods.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
public interface LostPeriodsService {

    LostPeriods getLostPeriods(PeriodDTO periodDTO) throws LostPeriodsException;

}
