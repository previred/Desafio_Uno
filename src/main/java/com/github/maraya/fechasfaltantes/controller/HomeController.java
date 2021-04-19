package com.github.maraya.fechasfaltantes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
    
    @GetMapping(value = "/periodos/fechas-faltantes/apidocs")
    public String doc() {
        return "redirect:../../swagger-ui.html";
    }
}