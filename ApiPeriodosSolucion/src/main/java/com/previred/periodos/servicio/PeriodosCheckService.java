package com.previred.periodos.servicio;

import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.ws.WebServiceException;
import com.previred.periodos.config.ConfiguracionAdicional;
import com.previred.periodos.swagger.codegen.model.Periodo;
import com.previred.periodos.swagger.codegen.model.PeriodoSol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; 

/**
 * Servicio que verifica la falta de fechas de un periodo, basado en el servicio generador
 * 
 * @author cerna14@gmail.com
 * @version 1.0.0 Otoño de 2020
 */
@Service

public class PeriodosCheckService {

    private static Logger log = LoggerFactory.getLogger(PeriodosCheckService.class);

    private String urlServer;
    private RestTemplate restTemplate;

    /**
     * constructor
     * 
     * @param builder RestTemplateBuilder
     * @param config  ConfiguracionAdicional
     */
    @Autowired
    public PeriodosCheckService(RestTemplateBuilder builder, ConfiguracionAdicional config) {
        this.urlServer = config.getUrlserver();
        /*
         * agregar un interceptor o handler para mostrar en log la ruta y cabecera que pasamos a servicio
         * GDD, util para depurar nuestra solución ante algun problema u otra configuracion adicional para
         * el Template rest
         */ 

        builder = builder.additionalInterceptors((request, body, execution) -> {
            log.info("request url: [{}]", request.getURI());
            log.info("request method: [{}]", request.getMethod());
            log.info("request headers: {}", request.getHeaders());
            return execution.execute(request, body);

        });
        log.info("tiempo maximo de intento conexion:{} ms", config.getConnectTimeout());
        log.info("tiempo maximo para leer el resultado:{} ms", config.getReadTimeout());

        builder.setConnectTimeout(config.getConnectTimeout());
        builder.setReadTimeout(config.getReadTimeout());
        this.restTemplate = builder.build();
    }


    /**
     * Genera un Objeto PeriodoSol,con las fechas obtenidas del servicio generador y las que faltan
     * 
     * @return PeriodoSol
     */
    public PeriodoSol getPeriodos() {

        // 1.- obtener periodo desde servicio GDD
        Periodo periodo = invokeServiceGdd();

        // 2.- transformar al objeto de salida
        PeriodoSol result = buildPeriodosSolucion(periodo);

        // 3.- calcular las fechas faltantes
        /*
         * creamos una set ordenado, que tiene un costo de complejidad algoritmica de acceso O(log(n)),
         * pensando que el servicio pueda entregar millones de fechas, que perjudiquen la performance de la
         * siguiente logica ##logica##: por el periodo revisar si el día 01 de cada mes existe en la lista
         * obtenida desde el GDD, si no existe se registra en la lista de las faltantes
         */

        SortedSet<LocalDate> listGDD = new TreeSet<LocalDate>(periodo.getFechas());

        LocalDate fechaInit = periodo.getFechaCreacion();
        // desde inicio y fin, ambos extremos incluidos segun ejemplo
        while (fechaInit.isBefore(result.getFechaFin()) || fechaInit.isEqual(result.getFechaFin())) {
            if (!listGDD.contains(fechaInit))
                result.addFechasFaltantesItem(fechaInit);
            fechaInit = fechaInit.plusMonths(1); // proximo mes
        }
        return result;
    }

    /**
     * transforma de Periodo a PeriodoSol
     * 
     * @param periodo
     * @return PeriodoSol
     */
    private PeriodoSol buildPeriodosSolucion(Periodo periodo) {
        PeriodoSol result = new PeriodoSol();
        result.setId(periodo.getId());
        result.setFechaCreacion(periodo.getFechaCreacion());
        result.setFechaFin(periodo.getFechaFin());
        result.setFechas(periodo.getFechas());
        return result;
    }

    /**
     * invoca al servicio GDD
     * 
     * @return Periodo
     */
    private Periodo invokeServiceGdd() {
        if (log.isDebugEnabled())
            log.debug("Obtener fechas desde:[{}]", urlServer);

        Periodo periodo = restTemplate.getForObject(urlServer, Periodo.class);
        if (periodo == null)
            throw new WebServiceException("No ha sido posible obtener periodos (rsp==null), desde [" + urlServer + "]");

        return periodo;
    }
}
