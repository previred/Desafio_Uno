package me.jics

import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.rxjava3.core.Single
import jakarta.inject.Inject
import me.jics.client.GddClient
import me.jics.client.GddOperations
import me.jics.client.Period
import me.jics.service.IGddService
import me.jics.service.MissingPeriod
import spock.lang.Specification

@MicronautTest
class GddServiceSpec extends Specification {
    @Inject
    IGddService service;

    @Inject
    GddOperations client;

    void 'get all periods'() {
        given: 'response from client'
        Period mock = getMockPeriod()
        when: 'invoked service'
        Single<MissingPeriod> periodSingle = service.getMissingPeriods()
        MissingPeriod missingPeriod = periodSingle.blockingGet()
        then: 'return the response from the services invoked'
        client.retrievePeriod() >> getMockSingle()
        expect: 'indexes and other attributes should be the same'
        missingPeriod.id == mock.id
        missingPeriod.creationDate == mock.creationDate
        missingPeriod.endDate == mock.endDate
        missingPeriod.dates == mock.dates
    }

    static def getMockSingle() {
        return Single.just(getMockPeriod())
    }

    static def getMockPeriod() {
        return MockGddController.mock()
    }

    @MockBean(GddClient)
    GddOperations gddClient() {
        Mock(GddOperations)
    }
}