package com.previred.lost.periods.services.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.previred.lost.periods.domain.dto.PeriodDTO;
import com.previred.lost.periods.domain.entities.LostPeriods;
import com.previred.lost.periods.domain.global.Constants;
import com.previred.lost.periods.exception.LostPeriodsException;
import com.previred.lost.periods.services.LostPeriodsService;

import lombok.extern.slf4j.Slf4j;

/**
 * Implements service operations of facade {@link LostPeriodsService}. Define
 * business logic to manage lost periods.
 * 
 * @author Carlos Izquierdo
 * @author izqunited@gmail.com
 *
 */
@Slf4j
@Service
public class LostPeriodsServiceImpl implements LostPeriodsService {

    /**
     * Calculate and return a set of lost dates (periods) between two different
     * dates, including only first day dates of each month of this range. A lost
     * period is defined as a first day of month date that is not inside of set
     * {@link PeriodDTO#getFechas()}.<br/>
     * <i>NOTE: Both {@link LostPeriods#fechaCreacion} and
     * {@link LostPeriods#fechaFin} are included as a possible lost dates to insert
     * in {@link LostPeriods#fechasFaltantes} list.</i>
     * 
     * @param periodDTO Contains the response data of a GDD Service call.
     * @return Object with the request information and the list of lost periods
     *         calculated.
     * @throws LostPeriodsException Custom wrapper exception thrown by the service
     *                              class in case of any error.
     * @see LostPeriods
     * @see PeriodDTO
     */
    @Override
    public LostPeriods getLostPeriods(PeriodDTO periodDTO) throws LostPeriodsException {
        LostPeriods lostPeriods = new LostPeriods();

        try {

            lostPeriods.setId(periodDTO.getId());
            lostPeriods.setFechaCreacion(periodDTO.getFechaCreacion());
            lostPeriods.setFechaFin(periodDTO.getFechaFin());
            lostPeriods.setFechas(periodDTO.getFechas());

            List<LocalDate> fechasFaltantes = new ArrayList<>();

            log.info("Calculating lost periods for request ID: " + periodDTO.getId() + " between dates '"
                    + periodDTO.getFechaCreacion() + "' and '" + periodDTO.getFechaFin() + "'");

            log.debug("List of dates inside range before lost period calculate process: " + periodDTO.getFechas());

            // Calculates months difference number between the two dates "fechaCreacion" and
            // "fechaFin". Method withDayOfMonth(1) is used to ensure that these dates are
            // of type
            // "first day of month date".

            long monthsBetween = ChronoUnit.MONTHS.between(periodDTO.getFechaCreacion().withDayOfMonth(1),
                    periodDTO.getFechaFin().withDayOfMonth(1));

            // Calculate all possible dates inside of "fechaCreacion"-"fechaFin" interval,
            // including
            // edges and verify if each one exists inside of periodDTO.getFechas() list. If
            // is not, add
            // these dates to "fechasFaltantes" list.

            for (long months = 0; months <= monthsBetween; months++) {
                LocalDate tempDate = periodDTO.getFechaCreacion().plusMonths(months).withDayOfMonth(1);

                if (!periodDTO.getFechas().contains(tempDate)) {
                    fechasFaltantes.add(tempDate);
                }
            }

            lostPeriods.setFechasFaltantes(fechasFaltantes);

            log.info("Lost periods calculated!");
            log.debug("List of lost periods calculated: " + lostPeriods.getFechasFaltantes());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new LostPeriodsException(Constants.LOST_PERIODS_CALCULATE_EXCEPTION_MESSAGE, e);
        }

        return lostPeriods;
    }

}
