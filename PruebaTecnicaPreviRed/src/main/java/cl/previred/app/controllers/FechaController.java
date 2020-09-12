package cl.previred.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.previred.app.data.dto.ErrorDto;
import cl.previred.app.data.dto.ResponseDto;
import cl.previred.app.services.IFechaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "/app", tags = "Controlador para obtener fechas restantes")
@Controller
@RequestMapping("/app")
@CrossOrigin(origins = "*")
public class FechaController {
	
	@Autowired
	private IFechaService ifechaService;
	

	@ApiOperation(value = "/consulta-global", response = ResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Exito", response = ResponseDto.class),
			@ApiResponse(code = 401, message = "No Autorizado", response = ErrorDto.class),
			@ApiResponse(code = 403, message = "Error de Información", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "No Encontrado", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "Error de programación", response = ErrorDto.class)
	})
	@GetMapping("/consulta-global")
	public ResponseEntity<?> getFechasFaltantes(){
			return ResponseEntity.status(HttpStatus.OK).body(ifechaService.getFechasRestantes());
	}
	
}
