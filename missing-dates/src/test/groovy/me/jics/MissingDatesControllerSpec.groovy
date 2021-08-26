package me.jics

import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.annotation.Client
import io.micronaut.rxjava3.http.client.Rx3HttpClient
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import me.jics.service.IGddService
import me.jics.service.MissingPeriod
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate

@MicronautTest
class MissingDatesControllerSpec extends Specification {

    @Shared
    @AutoCleanup
    @Inject
    @Client("/missing-dates")
    Rx3HttpClient client

    @Inject
    IGddService service;

    void "get all missing dates"() {
        given: 'response from the services'
        MissingPeriod mock = getMockMissingPeriod()
        when: 'request is made'
        Flowable flowable = client.retrieve(HttpRequest.GET('/'), Argument.of(MissingPeriod))
        MissingPeriod missingPeriod = flowable.firstElement().blockingGet()
        then: 'the services response the mock'
        service.getMissingPeriods() >> getMockSingle()
        expect: 'Indexes are equal and dates aren\'t equal'
        missingPeriod.id == mock.id
        assert missingPeriod.dates != missingPeriod.missingDates
    }

    void "get all missing dates bad"() {
        given: 'response from the services'
        MissingPeriod mock = getMockMissingPeriod()
        when: 'request is made'
        Flowable flowable = client.retrieve(HttpRequest.GET('/'), Argument.of(MissingPeriod))
        MissingPeriod missingPeriod = flowable.firstElement().blockingGet()
        then: 'the services response the mock'
        service.getMissingPeriods() >> getMockSingle()
        expect: 'Indexes are equal and dates aren\'t equal'
        missingPeriod.id == mock.id
        assert missingPeriod.dates != missingPeriod.missingDates
    }


    static def getMockSingle() {
        return Single.just(getMockMissingPeriod())
    }

    static def getMockMissingPeriod() {
        LocalDate creationDate = LocalDate.of(1969, 3, 1)
        LocalDate endDate = LocalDate.of(1970, 1, 1)
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(1969, 3, 1),
                LocalDate.of(1969, 5, 1),
                LocalDate.of(1969, 9, 1),
                LocalDate.of(1970, 1, 1)
        )
        List<LocalDate> missingDates = new LinkedList<>();
        for (LocalDate date = creationDate; date.isBefore(endDate); date = date.plusMonths(1)) {
            if (dates.stream().noneMatch(localDate -> localDate == date)) {
                missingDates.add(date);
            }
        }
        return MissingPeriod.builder()
                .id(1)
                .creationDate(creationDate)
                .endDate(endDate)
                .dates(dates)
                .missingDates(missingDates)
                .build()
    }
}
