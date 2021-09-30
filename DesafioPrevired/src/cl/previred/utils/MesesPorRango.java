package cl.previred.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
*
* @author villalobosmario@gmail.com
* 
*/

public class MesesPorRango {

	private String fecIni;
	private String fecFin;
	private int difMeses;
	private int agnoIni;
	private int mesIni;
	

	public MesesPorRango(String fecIni, String fecFin) {
		this.setFecIni(fecIni);
		this.setFecFin(fecFin);
	}
	
	public void calcularDifMeses()  {  
		try {
		// Se obtiene la diferencia en meses entre la fecha de creacion y fecha fin
					Calendar inicio = new GregorianCalendar();
					Calendar fin = new GregorianCalendar();
					inicio.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.getFecIni()));
					fin.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.getFecFin()));
					int difAgnos = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
					this.setDifMeses(difAgnos * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH) + 1);
					// Se obtienen el agno y mes de inicio del rango
					this.setAgnoIni(inicio.get(Calendar.YEAR));
					this.setMesIni(mesIni = inicio.get(Calendar.MONTH) + 1);
			
		}
		catch(ParseException e) {
			System.out.println("Error en el formato de las fechas");
			e.printStackTrace();
		}
	}

	public String getFecIni() {
		return fecIni;
	}

	public void setFecIni(String fecIni) {
		this.fecIni = fecIni;
	}

	public String getFecFin() {
		return fecFin;
	}

	public void setFecFin(String fecFin) {
		this.fecFin = fecFin;
	}

	public int getDifMeses() {
		return difMeses;
	}

	public void setDifMeses(int difMeses) {
		this.difMeses = difMeses;
	}

	public int getAgnoIni() {
		return agnoIni;
	}

	public void setAgnoIni(int agnoIni) {
		this.agnoIni = agnoIni;
	}

	public int getMesIni() {
		return mesIni;
	}

	public void setMesIni(int mesIni) {
		this.mesIni = mesIni;
	}	

}
