package me.jics.client;

import io.micronaut.retry.annotation.Fallback;
import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;
import java.util.Arrays;

@Fallback
public class GddClientFallback implements GddOperations {
    @Override
    public Single<Period> retrievePeriod() {
        return Single.just(Period.builder()
                .id(0)
                .creationDate(LocalDate.of(1969, 3, 1))
                .endDate(LocalDate.of(1970, 1, 1))
                .dates(Arrays.asList(
                        LocalDate.of(1969, 3, 1),
                        LocalDate.of(1969, 5, 1),
                        LocalDate.of(1969, 9, 1),
                        LocalDate.of(1970, 1, 1)
                ))
                .build());
    }
}
