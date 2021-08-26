package me.jics

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import me.jics.client.GddClient
import me.jics.client.GddOperations
import me.jics.client.Period
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class GddClientSpec extends Specification {
    @AutoCleanup
    @Shared
    EmbeddedServer server = ApplicationContext.run(EmbeddedServer) as EmbeddedServer

    @Shared
    @Subject
    GddClient client = server.applicationContext.getBean(GddOperations) as GddClient

    void 'fetch period'() {
        given:
        Period mock = MockGddController.mock()
        when:
        Period period = client.retrievePeriod().blockingGet()
        then:
        period.id == mock.id
    }


}