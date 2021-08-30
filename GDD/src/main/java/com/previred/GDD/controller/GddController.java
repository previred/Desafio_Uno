package com.previred.GDD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.previred.GDD.model.GddRequest;
import com.previred.GDD.model.GddResponse;
import com.previred.GDD.service.GddService;

@RestController
public class GddController {
	
	@Autowired
	GddService gddService;
	
	@RequestMapping(value = "/GDD", method = RequestMethod.POST)
	public GddResponse GDD(@RequestBody GddRequest request) {
		return gddService.getPeriodos(request);
	}
	
}
