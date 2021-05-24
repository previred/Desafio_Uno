package org.pr3v1r3d.desafio1.controller;

import org.pr3v1r3d.desafio1.model.date.PopulateMissingResponse;
import org.pr3v1r3d.desafio1.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/date")
public class DateController {

    @Autowired
    private DateService dateService;

	@GetMapping("/missing")
	 ResponseEntity<PopulateMissingResponse> populateMissing() {
		return new ResponseEntity<PopulateMissingResponse>(dateService.populateMissing(), HttpStatus.OK);
	}

}
