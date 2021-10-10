package desafiocuatro.periodos.datasource;

import desafiocuatro.periodos.swagger.codegen.model.Periodo;
import desafiocuatro.periodos.swagger.codegen.client.PeriodosApi;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author josem
 */
        
@Component("desafiocuatro.periodos.datasource.DatasourceRest")
@ComponentScan("desafiocuatro.periodos.client")
public class DatasourceRest {

    private RestTemplate restTemplate;
    
    @Autowired
    private PeriodosApi periodosApi;

    public DatasourceRest(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public Periodo getPeriodos() {
        
        Periodo response = periodosApi.periodos();
        return response;
    }
}
