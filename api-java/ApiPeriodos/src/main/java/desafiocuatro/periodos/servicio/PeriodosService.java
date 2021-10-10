package desafiocuatro.periodos.servicio;

import org.springframework.stereotype.Service;
import desafiocuatro.periodos.swagger.codegen.model.Periodo;
import desafiocuatro.periodos.datasource.DatasourceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ComponentScan;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.lang.Boolean;

@Service
@Component("desafiocuatro.periodos.servicio.PeriodosService")
@ComponentScan("desafiocuatro.periodos.datasource")
public class PeriodosService {

    @Autowired
    private DatasourceRest datasourceRest;

    public Periodo getPeriodos() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        Periodo periodo = datasourceRest.getPeriodos();

        LocalDate fechaCreacion = periodo.getFechaCreacion();
        LocalDate fechaFin = periodo.getFechaFin();

        if (fechaCreacion == null || fechaFin == null) {
            return periodo;
        }

        List<LocalDate> fechasFaltantes = new ArrayList<>();
        List<LocalDate> fechas = periodo.getFechas();

        for (LocalDate date = fechaCreacion; date.isBefore(fechaFin); date = date.plusMonths(1)) {
            if (fechas.size() > 0) {
                Boolean found = false;
                
                for (LocalDate f : fechas) {
                    if (date.isEqual(f)){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    fechasFaltantes.add(date);
                }
            } else {
                fechasFaltantes.add(date);
            }

        }
        periodo.setFechasFaltantes(fechasFaltantes);
        return periodo;
    }
}
