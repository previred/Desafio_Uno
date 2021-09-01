package com.previred.vc.mejoraperiodos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.previred.vc.mejoraperiodos.model.Periodo;
import com.previred.vc.mejoraperiodos.model.PeriodoFaltante;
import com.previred.vc.mejoraperiodos.service.PeriodoFaltanteService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Slf4j
@Controller
@Api("API PeriodosFaltantes desarrollada por Victor Cifuentes")
public class ApiPeriodosFaltantes {

    @Autowired
    private PeriodoFaltanteService periodoFaltanteService;

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PeriodoFaltante> periodos(){
        try {
        	long t1 = System.currentTimeMillis();
        	Periodo periodo = periodoFaltanteService.callAPIPrevired();
        	PeriodoFaltante faltentes = periodoFaltanteService.getPeriodosFaltantes(periodo);
            log.info("Time API Faltantes: "+(System.currentTimeMillis()-t1)+ " milisegundos");
        	return new ResponseEntity<>(faltentes, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
