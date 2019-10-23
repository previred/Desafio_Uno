package cl.previred.challenge.periods.service;

import cl.previred.challenge.periods.model.periods.MissingPeriodsResponse;
import cl.previred.challenge.periods.rest.gdd.GddResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.ArrayList;

/**
 * Service to complete periods from GddService
 *
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
@Service
public class MissingPeriodsService {

    @Autowired
    private GddService gddService;

    public MissingPeriodsResponse completePeriods() {
        MissingPeriodsResponse response;

        GddResponse gdd = gddService.getGdd();

        response = new MissingPeriodsResponse(gdd.getId(), gdd.getFechaCreacion(), gdd.getFechaFin(), gdd.getFechas());

        completeMissing(response);

        return response;
    }

    private void completeMissing(MissingPeriodsResponse to){

        to.setFechasFaltantes(new ArrayList<>());
        LocalDate toIterate = to.getFechaCreacion().with(ChronoField.DAY_OF_MONTH, 1);

        if(toIterate.isEqual(to.getFechaCreacion()) && !to.getFechas().contains(toIterate)){
            to.getFechasFaltantes().add(toIterate);
        }

        while (toIterate.isBefore(to.getFechaFin())) {
            toIterate = toIterate.plusMonths(1);
            if (!to.getFechas().contains(toIterate)) {
                to.getFechasFaltantes().add(toIterate);
            }
        }
    }
}
