package io.ajsoft.previred.desafio1.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.ajsoft.previred.desafio1.dto.InObjectDTO;
import io.ajsoft.previred.desafio1.dto.OutObjectDTO;
import io.ajsoft.previred.desafio1.helper.GDDClientHelper;

@RestController("/api")
public class DesafioUnoController {
	
	@Autowired
	GDDClientHelper gddClient;
	
	@GetMapping("/process")
	public OutObjectDTO process() {
		Gson parser = new Gson();
		InObjectDTO inObj = gddClient.callGDD();
		OutObjectDTO outObj = new OutObjectDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<LocalDate> inDates = inObj.getFechas()
				.stream()
				.map(date -> LocalDate.parse(date, formatter))
				.sorted()
				.collect(Collectors.toList());
	    
		System.out.println("In object is: \n"+parser.toJson(inObj));
		
		outObj.setId(inObj.getId());
		outObj.setFechaCreacion(inObj.getFechaCreacion());
		outObj.setFechaFin(inObj.getFechaFin());
		outObj.setFechas(inDates
				.stream()
				.map(dateStr -> formatter.format(dateStr))
				.collect(Collectors.toList()));
		
		LocalDate from = LocalDate.parse(inObj.getFechaCreacion(), formatter);
		LocalDate until = LocalDate.parse(inObj.getFechaFin(), formatter);
        int minYear = from.getYear();
        int maxYear = until.getYear();
        int firstMonth = from.getMonthValue();
        int lastMonth = until.getMonthValue();
        
        for (int year = minYear; year <= maxYear; year++) {
        	int month = (year == minYear ? firstMonth : 1);
        	for (; year < maxYear ? month <= 12 : month <= lastMonth; month++) {
        		String ddate = year+"-"+String.format("%02d", month)+"-01";
        		if (!outObj.getFechas().contains(ddate) 
        				&& !ddate.equals(outObj.getFechaCreacion())
        				&& !ddate.equals(outObj.getFechaFin()))
        			outObj.getFechasFaltantes().add(ddate);
        	}
        }
        
        System.out.println("-----------------\nOut object is: \n"+parser.toJson(outObj));
		return outObj;
	}

}
