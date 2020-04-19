package com.previred.periodos.determinafaltantes.core

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDate

class DeterminarPeriodosPerdidosServiceSpec extends Specification{
    @Shared private PeriodoPort port
    @Subject @Shared private DeterminarPeriodosPerdidosUseCase service

    void setup() {
        port = Stub(PeriodoPort)
        service = new DeterminarPeriodosPerdidosService(port)

    }

    def "al obtener un Periodo nulo, se retorna un error de servicio no disponible"(){
        given:
        port.getFechasPeriodoAleatorio() >> Periodo.PERIODO_NULO
        when:
        service.calcular()
        then:
        def e = thrown(PeriodoDataSourceNoDisponible)
        e.message.contains("La consulta de las fechas del period aleatorio no fue satisfactoria")
    }

    def "al obtener un Periodo sin fechas faltantes, la lista de estas es vacia"(){
        given:
        def respuestaPort = Periodo.builder().id(1)
                .fechaCreacion(LocalDate.of(2020, 2, 1))
                .fechaFin(LocalDate.of(2020, 4, 1))
                .fechas([LocalDate.parse("2020-03-01")])
                .build()
        port.getFechasPeriodoAleatorio() >> respuestaPort

        when:
        PeriodoConsFaltantes periodo = service.calcular()

        then:
        with(periodo){
            getFechasFaltantes().isEmpty()
            getId() == respuestaPort.getId()
            getFechaCreacion() == respuestaPort.getFechaCreacion()
            getFechaFin() == respuestaPort.getFechaFin()
            getFechas().containsAll(respuestaPort.getFechas())
        }
    }



}
