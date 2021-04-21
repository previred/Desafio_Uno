package cl.previred.desafio.uno.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.previred.desafio.uno.dto.PeriodResponse;
import cl.previred.desafio.uno.services.GDDService;

@RestController()
@RequestMapping("api")
public class ApiController {
	@Autowired
	private GDDService gddService;
	
	@GetMapping(value = "gdd")
	public PeriodResponse getFullDateList()  throws Exception{
		return new PeriodResponse(gddService.getDates());
	}

}
