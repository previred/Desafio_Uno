package me.jics.service;

import io.reactivex.rxjava3.core.Single;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import me.jics.client.GddClient;
import me.jics.client.GddOperations;
import me.jics.exception.DateRangeNotValidException;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Service layer abstraction from data.
 *
 * @author Juan Cuzmar
 * @version 1.0
 */
@Slf4j
@Singleton
public class GddService implements IGddService {

    private final GddOperations client;

    /**
     * Constructor of the GddService class
     * Here we are injecting some dependencies
     *
     * @param client the http client layer
     */
    public GddService(GddClient client) {
        this.client = client;
    }

    /**
     * Here we take care of getting the missing dates given two dates.
     * The missing dates must not be the same as the ones delivered by the client http
     *
     * @return single result of missing date already processed
     */
    @Override
    public Single<MissingPeriod> getMissingPeriods() {
        log.info("Entering to the service method: getMissingPeriods");
        return this.client.retrievePeriod()
                .doOnSuccess(period -> log.info(String.format("Data successfully obtained with id: %d", period.getId())))
                .map(period -> {
                    if (period.getCreationDate().isAfter(period.getEndDate())) {
                        throw new DateRangeNotValidException(
                                String.format("The services delivered dates don't fulfill the required range to do the process: creationDate=%s endDate=%s", period.getCreationDate(), period.getEndDate())
                        );
                    }
                    List<LocalDate> missingDates = new LinkedList<>();
                    if (log.isDebugEnabled()) {
                        log.debug(period.toString());
                    }
                    for (LocalDate date = period.getCreationDate(); date.isBefore(period.getEndDate()); date = date.plusMonths(1)) {
                        final LocalDate immutableDate = date;
                        if (log.isDebugEnabled()) {
                            log.debug(String.format("Next date=%s", immutableDate));
                            log.debug("Search for matches");
                        }
                        boolean match = period.getDates().stream().noneMatch(localDate -> {
                            boolean isEqual = localDate.equals(immutableDate);
                            if (log.isDebugEnabled()) {
                                log.debug(String.format("Comparing localDate=%s, immutableDate=%s, equals=%b", immutableDate, immutableDate, isEqual));
                            }
                            return isEqual;
                        });
                        if (match) {
                            if (log.isDebugEnabled()) {
                                log.debug(String.format("Adding date=%s", immutableDate));
                            }
                            missingDates.add(date);
                        }
                    }
                    return MissingPeriod.builder()
                            .id(period.getId())
                            .creationDate(period.getCreationDate())
                            .endDate(period.getEndDate())
                            .dates(period.getDates())
                            .missingDates(missingDates)
                            .build();
                })
                .doFinally(() -> log.info("Date search process successfully completed"))
                .doOnError(throwable -> log.error(throwable.getLocalizedMessage()));
    }
}
