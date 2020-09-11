package cl.previred.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.previred.app.services.IFechaService;

@Controller
@RequestMapping("/app")
@CrossOrigin(origins = "*")
public class FechaController {
	
	@Autowired
	private IFechaService ifechaService;
	

	@GetMapping("/consulta-global")
	public ResponseEntity<?> getFechasFaltantes(){
			return ResponseEntity.status(HttpStatus.OK).body(ifechaService.getFechasRestantes());
	}
	
}
