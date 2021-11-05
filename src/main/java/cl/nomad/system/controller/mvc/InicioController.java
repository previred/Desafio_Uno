package cl.nomad.system.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;

import cl.nomad.service.ResultadoService;

@Controller
@RequestMapping
public class InicioController implements ErrorController {

	@Autowired
	private ResultadoService resultService;

	@GetMapping({ "/" })
	public ModelAndView getResultado() throws JsonProcessingException {
		
		ModelAndView modelAndView = new ModelAndView("desafio/resultado");
		String res = this.resultService.buscarDesafio();

		modelAndView.addObject("resultado", res);

		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
