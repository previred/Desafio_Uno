package com.cox.igdd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cox.igdd.models.OutListadoFechas;
import com.cox.igdd.repository.SolicitarFechasRepo;

@Service
public class SolicitarFechasServicesImpl implements SolicitarFechasService {

	@Autowired
	private SolicitarFechasRepo solicitarFechasRepo;
	
	 
	@Override
	public OutListadoFechas invocar() {
		// TODO Auto-generated method stub
		
		return solicitarFechasRepo.invocar();
		
	}





}
