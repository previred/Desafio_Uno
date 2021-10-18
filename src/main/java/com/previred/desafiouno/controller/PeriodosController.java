package com.previred.desafiouno.controller;

import com.previred.desafiouno.dto.BaseResponseDTO;
import com.previred.desafiouno.dto.PeriodosPerdidosDTO;
import com.previred.desafiouno.exception.FechaCreacionNulaException;
import com.previred.desafiouno.exception.FechaFinNulaException;
import com.previred.desafiouno.exception.RestClientException;
import com.previred.desafiouno.service.PeriodoService;
import com.previred.desafiouno.type.ResultCode;
import com.previred.desafiouno.util.MessageSourceUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Api(value = "API para gestion sobre periodos")
@Controller
@RequestMapping("/periodos")
public class PeriodosController {

	@Autowired
	private PeriodoService periodoService;

	@GetMapping("/faltantes")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtiene el listado de los periodos faltandos de lo obtenido en servicio GDD", response = BaseResponseDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Periodo y lista de fechas faltantes", response = BaseResponseDTO.class),
			@ApiResponse(code = 409, message = "Error en la validacion de datos de servicio GDD", response = BaseResponseDTO.class),
			@ApiResponse(code = 502, message = "Error en la invoacion de servicio GDD", response = BaseResponseDTO.class)})
	@ResponseBody
	public BaseResponseDTO<PeriodosPerdidosDTO> obtienePeriodosFaltantes() throws FechaCreacionNulaException, FechaFinNulaException, RestClientException {
		PeriodosPerdidosDTO periodosPerdidosDTO = periodoService.getPeriodosFaltantesFromGDD();
		return new BaseResponseDTO<>(ResultCode.SUCCESS, MessageSourceUtil.getValue("msge.servicio.exitoso"), periodosPerdidosDTO);
	}
}
