package cl.nomad.system.controller.mvc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.nomad.service.ResultadoService;
import cl.nomad.system.data.entity.Resultado;

@RestController
@RequestMapping("/rest/")
public class ResultadoController {
    private Log logger = LogFactory.getLog(getClass());
    @Autowired
    private ResultadoService resultadoService;


    @GetMapping(value="resultado", produces = { "application/json" })
    public Resultado getResultadoRest(){
        return this.resultadoService.buscarJson();
    }

}
