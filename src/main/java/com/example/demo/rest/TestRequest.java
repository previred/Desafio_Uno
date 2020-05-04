package com.example.demo.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TestService;

@RestController
@RequestMapping("/api")
public class TestRequest {
	
	@Autowired
	private TestService testService;
	
	// @RequestBody
	@GetMapping("/test")
	public String testSend( ) throws IOException {
		
		// aca se instancia la clase con la logica de negocio
		return testService.testService();

	}
}
