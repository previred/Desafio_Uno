package cl.prueba.previred.service

import cl.prueba.previred.model.GDFechas
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification


class ServicesSpec extends Specification {

    RestTemplate restTemplate = Stub()
    GDFechasService gdFechasService
    def listFechas = ["1992-12-01", "1993-03-01", "1993-04-01", "1993-07-01", "1993-08-01", "1994-03-01", "1994-05-01", "1994-06-01",
                      "1994-09-01", "1994-12-01", "1995-01-01", "1995-02-01", "1995-10-01", "1996-06-01", "1996-08-01", "1996-10-01",
                      "1996-11-01", "1997-01-01", "1997-08-01", "1997-10-01", "1998-03-01", "1998-10-01", "1998-11-01", "1998-12-01",
                      "1999-01-01", "1999-04-01", "1999-12-01", "2000-01-01", "2000-11-01", "2001-04-01", "2001-08-01",
                      "2002-01-01", "2002-05-01", "2002-07-01", "2002-08-01", "2003-01-01", "2003-06-01", "2003-07-01", "2003-11-01",
                      "2004-01-01", "2004-02-01", "2004-10-01", "2004-11-01", "2005-05-01", "2005-07-01",
                      "2005-10-01", "2006-01-01", "2006-06-01", "2006-07-01", "2006-08-01", "2006-09-01", "2006-12-01", "2007-01-01",
                      "2007-07-01", "2007-10-01", "2007-12-01", "2008-02-01", "2008-04-01", "2008-07-01", "2008-08-01", "2008-10-01", "2008-12-01",
                      "2009-09-01", "2009-11-01", "2010-03-01", "2010-07-01", "2010-10-01", "2011-01-01", "2011-03-01", "2011-08-01",
                      "2011-09-01", "2011-12-01", "2012-01-01", "2012-02-01", "2012-03-01", "2012-12-01", "2013-12-01",
                      "2014-01-01", "2014-03-01", "2015-01-01", "2015-04-01", "2015-09-01", "2015-11-01", "2016-03-01", "2016-05-01",
                      "2016-07-01", "2016-08-01", "2017-01-01", "2017-06-01", "2017-08-01", "2017-09-01", "2017-10-01"]

    def setup() {
        gdFechasService = new GDFechasServiceImpl()
        gdFechasService.restTemplate = restTemplate
    }

    def "El método getAllCoin consume endpoint y retorna lista de fechas"() {
        given: "configuran variables utiles para simular rest"
        def gdFechas = new GDFechas()
        gdFechas.id = 1
        gdFechas.fechaCreacion = "1992-09-01"
        gdFechas.fechaFin = "2017-11-01"
        gdFechas.fechas = listFechas
        restTemplate.getForEntity(_, _) >> new ResponseEntity<GDFechas>(gdFechas, HttpStatus.OK)
        when: "se invoca getallcoins"
        GDFechas coins = gdFechasService.getCoins()
        then: "se debe generar el valor de fechas faltantes, lo que es la respuesta para el servicio"
        coins
        coins.fechasFaltantes
    }

}