package me.jics

import io.micronaut.context.annotation.Requires
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.rxjava3.core.Single
import me.jics.client.Period

import java.time.LocalDate

@Requires(property = 'spec.name', value = 'mockGdd')
@Controller('/periods')
class MockGddController {
    @Get()
    Single<Period> index() {
        return Single.just(mock())
    }
    static Period mock() {
        return Period.builder()
                .id(1)
                .creationDate(LocalDate.of(1969, 3, 1))
                .endDate(LocalDate.of(1970, 1, 1))
                .dates(Arrays.asList(
                        LocalDate.of(1969, 3, 1),
                        LocalDate.of(1969, 5, 1),
                        LocalDate.of(1969, 9, 1),
                        LocalDate.of(1970, 1, 1)
                ))
                .build()
    }
}
