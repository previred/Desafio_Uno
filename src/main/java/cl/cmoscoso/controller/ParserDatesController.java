package cl.cmoscoso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.cmoscoso.dto.DatesParsedDTO;
import cl.cmoscoso.service.ParserDateService;

@RestController
@RequestMapping("/api/cmoscoso/parseDate/1.0")
public class ParserDatesController {
	@Autowired
	private ParserDateService parserDateService;

	@GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DatesParsedDTO> get1_0() throws Exception {
		DatesParsedDTO response = null;

		response = parserDateService.getParseDates();

		return new ResponseEntity<DatesParsedDTO>(response, HttpStatus.OK);
	}

}
