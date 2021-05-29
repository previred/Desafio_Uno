package com.desafio1.controller;


import org.springframework.stereotype.*;
import com.desafio1.repository.*;
import com.desafio1.model.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.desafio1.service.*;

@RestController
@RequestMapping("/base")
public class ClientController  {
	
	@Autowired
	ClientService clientService;

	@GetMapping(value = "/consolidadatos") 
	public Cont getdata() throws Exception {
		
		return clientService.completar();
	}
	

}
