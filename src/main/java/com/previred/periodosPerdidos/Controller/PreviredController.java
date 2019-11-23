package com.previred.periodosPerdidos.Controller;

import com.previred.periodosPerdidos.Model.Periodo;
import com.previred.periodosPerdidos.Service.PreviredService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matias Arce on 11/23/2019.
 */
@RestController
@RequestMapping("/api")
public class PreviredController {

    private static PreviredService previredService;

    @Autowired
    public PreviredController(PreviredService previredService) {
        this.previredService=previredService;
    }

    /**
     * Servicio REST Periodos Perdidos
     * @return
     *
     * ApiOperation -> MetaData Swagger
     */
    @ApiOperation(value = "Obtiene Periodos Perdidos",notes = "Obtiene periodos perdidos entre 2 fechas que no existen en servicio GDD",response = Periodo.class)
    @GetMapping("/periodosPerdidos")
    public Periodo periodosPerdidos(){
        Periodo periodo=new Periodo();
        try{
            periodo=previredService.getPeriodosPerdidos();
        }catch (Exception e){
            return periodo;
        }
        return periodo;
    }
}
