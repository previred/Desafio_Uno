package com.previred.vc.mejoraperiodos.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api("API PeriodosFaltantes")
public class ApiPeriodosFaltantes {

    @Autowired
    private PeriodoFaltanteService periodoFaltanteService;

	@RequestMapping(value = "/api", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PeriodoFaltante> periodos(@RequestBody Periodo periodo){
        try {
        	PeriodoFaltante faltentes = periodoFaltanteService.getPeriodosFaltantes(periodo);
            ResponseEntity<PeriodoFaltante> respuesta = new ResponseEntity<>(faltentes, HttpStatus.OK);
            return respuesta;
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	

	@RequestMapping(value = "/api", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PeriodoFaltante> periodos(){
        try {
        	Periodo periodo = periodoFaltanteService.callAPIPrevired();
        	return periodos(periodo);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
