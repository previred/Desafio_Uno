package com.previred.gdd.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.previred.gdd.model.Periodo;
import com.previred.gdd.service.GeneradorDatosService;

@RestController
@RequestMapping("/gdd")
public class GeneradorDatosController {

	@Autowired
	GeneradorDatosService gddService;
	
	@ResponseBody
	@GetMapping(path ="/nivel2" )
	public String textResult(HttpServletResponse response){
		Periodo periodos = gddService.getPeriodos();
		String fileName = "periodos.txt";
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    return periodos.toText();
	}
	@GetMapping(path="/nivel3", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Periodo jsonResult() {
		Periodo detalle = gddService.getPeriodos();
		return detalle;
	}
}
