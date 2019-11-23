package com.previred.periodosPerdidos.Repository;

import com.previred.periodosPerdidos.Exception.RepositoryException;
import com.previred.periodosPerdidos.Model.Periodo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by Matias Arce on 11/23/2019.
 */
@Repository
public class PreviredRepository {

    private static Environment env;
    private static RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public PreviredRepository(RestTemplateBuilder restTemplateBuilder,Environment env) {
        this.restTemplateBuilder=restTemplateBuilder;
        this.env=env;
    }


    /**
     * Consume servicio REST Generador De Datos o GDD
     * @return
     * @throws RepositoryException
     */
    public Periodo getPeriodos() throws RepositoryException {
        Periodo periodo=new Periodo();
        String url_servicio = env.getProperty("url.rest");
        try{
            periodo = restTemplateBuilder.build().getForObject(url_servicio, Periodo.class);
        } catch (HttpStatusCodeException ex){
            throw new RepositoryException(ex.getStatusCode().toString(),ex);
        } catch (Exception e){
            throw new RepositoryException("Error Servicio PreviredRepository",e);
        }
        return periodo;
    }


}
