package com.previred.uno.services.impl;

import com.previred.uno.comunes.Utils;
import com.previred.uno.exceptions.PreviredException;
import com.previred.uno.models.periodos.Periodos;
import com.previred.uno.services.PeriodosService;
import com.previred.uno.services.rest.impl.ObtenerPeriodosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación de PeriodosService,
 * encargada de manejar objeto de respuesta de Api
 *
 * @author pvillar
 */

@Service
@Slf4j
public class PeriodosServiceImpl implements PeriodosService{

    private ObtenerPeriodosServiceImpl obtenerPeriodosService;

    @Autowired
    public PeriodosServiceImpl(ObtenerPeriodosServiceImpl obtenerPeriodosService) {
        this.obtenerPeriodosService = obtenerPeriodosService;
    }

    @Override
    public Periodos obtenerPeriodos() throws PreviredException {
        log.info("Llamando a obtenerPeriodosService.getPeriodos()");
        Periodos periodosResponse = new Periodos(obtenerPeriodosService.getPeriodos());

        try {
            /**
             * Identificar meses faltantes en lista "meses"
             * y agregar meses faltantes a lista "mesesFaltantes" en objeto "periodosResponse"
              */
            setMesesFaltantes(periodosResponse);
            log.info("Respuesta generada de Periodos : {}", periodosResponse);
            return periodosResponse;

        }catch (Exception exception){
            log.error("Exception en setMesesFaltantes()", exception);
            throw new PreviredException(PreviredException.CodeException.ERROR_RESPONSE,"Error interno setMesesFaltantes()", exception);
        }
    }

    /**
     * Obtener y Agregar meses faltantes en Objeto Periodos.
     *
     * @param periodos objeto con periodos y fechas
     */
    private void setMesesFaltantes(Periodos periodos) {
        log.info("Identificando meses faltantes...");
        periodos.setFechasFaltantes(new Utils().getMesesFaltantes(periodos.getFechaCreacion(),
                periodos.getFechaFin(),
                periodos.getFechas()));

        log.info("Meses faltantes (size: {}) : {}", periodos.getFechasFaltantes().size(), periodos.getFechasFaltantes());
    }

}
