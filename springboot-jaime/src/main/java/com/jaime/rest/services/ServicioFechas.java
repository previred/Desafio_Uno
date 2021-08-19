package com.jaime.rest.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jaime.rest.model.FechasServicio;

@Service
public class ServicioFechas {
	
	public List<String> obtenerFechasRestantes(FechasServicio fechasCiente) { 
		
		try {
			
			System.out.println("fechasCiente: " + fechasCiente );
			
			String formatoFecha = "yyyy-MM-dd"; 
			List<String> fechasRestantes = new ArrayList<String>(); 
			
			Date fechaInicial = new SimpleDateFormat(formatoFecha).parse(fechasCiente.getFechaCreacion());
			Date fechaFinal = new SimpleDateFormat(formatoFecha).parse(fechasCiente.getFechaFin());
			Date nuevaFecha = fechaInicial;  
			
			boolean bandera = true;
			while (bandera) { 
				
				Calendar c1 = Calendar.getInstance();
	            c1.setTime(nuevaFecha);
	            c1.add(Calendar.MONTH, 1);
	            nuevaFecha = c1.getTime(); 

				boolean esEntreFechas = nuevaFecha.compareTo(fechaInicial) >= 0 && nuevaFecha.compareTo(fechaFinal) <=0; 				
				if ( esEntreFechas ) { 
					
					String fechaNuevaString = new SimpleDateFormat(formatoFecha).format(nuevaFecha); 
					
					if ( !fechasCiente.getFechas().contains(fechaNuevaString)) { 
						fechasRestantes.add(fechaNuevaString); 
					}
					
				} else { 
					bandera = false; 
				}
				
			}
			
			return fechasRestantes; 
			
		} catch ( Exception ex ) { 
			System.err.println(ex.getMessage());
			throw new RuntimeException(ex.getMessage()); 
		}
		
	}
	
}
