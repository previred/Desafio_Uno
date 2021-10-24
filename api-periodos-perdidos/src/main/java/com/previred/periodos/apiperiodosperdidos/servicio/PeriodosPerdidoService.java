package com.previred.periodos.apiperiodosperdidos.servicio;

import com.previred.periodos.apiperiodosperdidos.swagger.codegen.model.PeriodoPerdido;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

/**
 *  Clase de servicio de de Pariosdos Peiddo del proyecto
 *  sus metodos son necesarios para la comunicación con el
 *  servicio anterior de pedidos y los nuevos requeimientos.
 * @author cesar.ogalde@gmail.com
 */
@Service
public class PeriodosPerdidoService {

    /**
     * parámetro de la url de servicio 
     * formato http://[host:puerto]/[path]
     * este se llena con la llave api.pedidos.url que está en el archivo
     * application.properties dentro del proyecto. 
     */
    @Value("${api.pedidos.url}")
    private String apiPedidosUrl;

    /**
     * Obtiene las fechas del api de pedidos original y de la lista de fechas obtenidas
     * genera la lista de fecha faltantas incorporadola como atributo a la respuesta.
     * @return lista de fechas pedidos + fechas faltantes
     */
    public PeriodoPerdido getPeriodos() throws Exception {
        PeriodoPerdido periodo = call(new RestTemplate());

        // lógica de obtención fechas faltantes
        periodo.setFechasFaltantes(new ArrayList<>());
        LocalDate fecha_inicial = periodo.getFechaCreacion();
        do {

            if (!periodo.getFechas().contains(fecha_inicial)) {
                periodo.getFechasFaltantes().add(fecha_inicial);
            }
            fecha_inicial = fecha_inicial.plusMonths(1l);

        } while (!fecha_inicial.isAfter(periodo.getFechaFin()));

        return periodo;
    }

    /**
     * llamada cliente a servicio API
     * 
     * @param restTemplate rest template para invocar el servicio
     * @return devuelve Periodos existentes.
     * @throws Exception
     */
    private PeriodoPerdido call(RestTemplate restTemplate) throws Exception {
        PeriodoPerdido periodo = restTemplate.getForObject(apiPedidosUrl, PeriodoPerdido.class);
        return periodo;
    }
}
