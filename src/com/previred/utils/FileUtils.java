package com.previred.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

public class FileUtils {

	public void escribirSalida(List<LocalDate> listaFaltantes, List<LocalDate> listaRecibidas, LocalDate fechaCreacion, LocalDate fechaFin) {
		Path path = Paths.get("c:/salida/output.txt");
		 
		try (BufferedWriter writer = Files.newBufferedWriter(path)){
		    writer.write("SALIDA: ");
		    writer.newLine();
		    writer.write("fecha creación: " + fechaCreacion);
		    writer.newLine();
		    writer.write("fecha fin: " + fechaFin);
		    writer.newLine();
		    
		    writer.write("fechas recibidas: ");
		    for (LocalDate recibida : listaRecibidas) {
				writer.write(recibida + ", ");
			}
		    
		    writer.newLine();
		    
		    writer.write("fechas faltantes: " );
		    for (LocalDate faltante : listaFaltantes) {
				writer.write(faltante + ", ");
			}
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
