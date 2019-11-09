package com.previred.periodos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.periodos.beans.Periodo;
import com.previred.periodos.beans.PeriodoResponse;

@Service
public class ServicioDesafioUno {

	@Autowired
	private RestTemplate restTemplate;

	public PeriodoResponse fechasFaltantes( ) {



		Periodo periodo = restTemplate.getForObject("http://localhost:8080/periodos/api", Periodo.class);

		/*Periodo periodo = new Periodo();

		llenarPeriodoManual(periodo);
		 */


		ArrayList<LocalDate> listaFechas = (ArrayList<LocalDate>) periodo.getFechas();

		ArrayList<LocalDate> listaFechasIntermedias = new ArrayList<LocalDate>();

		ArrayList<LocalDate> listaFechasFaltantes = new ArrayList<LocalDate>();

		LocalDate ld = periodo.getFechaCreacion();
		Iterator<LocalDate> it = listaFechas.iterator();

		LocalDate fechaCre = periodo.getFechaCreacion();
		LocalDate fechaFin = periodo.getFechaFin();


		//System.out.println(listaFechas+ "********");

		int i = 0;
		while (it.hasNext()) {

			LocalDate lf = it.next();

			listaFechasIntermedias =verificarFaltaFechas(ld, lf,listaFechasFaltantes, listaFechas ,0,i,fechaCre);
			ld = lf;
			i++;
		}		

		while(!ld.equals(fechaFin)) {
			if(!buscarEnListaExistentes(ld, listaFechas)) {
				listaFechasIntermedias.add(ld);
			}

			ld = ld.plusMonths(1);

		}

		if(ld.equals(fechaFin)) {
			if(!buscarEnListaExistentes(ld, listaFechas)) {
				listaFechasIntermedias.add(fechaFin);
			}
		}
		it = listaFechasIntermedias.iterator();		
		while (it.hasNext()) {
			LocalDate lf = it.next();
			System.out.println(lf);
		}		

		PeriodoResponse pr = new PeriodoResponse();

		pr.setId(periodo.getId());
		pr.setFechaCreacion(periodo.getFechaCreacion());
		pr.setFechas(periodo.getFechas());
		pr.setFechasFaltantes(listaFechasIntermedias);
		pr.setFechaFin(periodo.getFechaFin());

		return pr;

	}

	private void llenarPeriodoManual(Periodo periodo) {
		periodo.setFechaCreacion(LocalDate.of(1969,03,01));//1969-03-01

		periodo.setFechaFin(LocalDate.of(1970,01,01));//1970-01-01

		LocalDate f1 = LocalDate.of(1969,03,01);	
		LocalDate f2 = LocalDate.of(1969,05,01);		
		LocalDate f3 = LocalDate.of(1969,9,01);
		LocalDate f4 = LocalDate.of(1970,01,01);
		LocalDate f5 = LocalDate.of(2019,05,01);
		LocalDate f6 = LocalDate.of(2019,06,01);
		LocalDate f7 = LocalDate.of(2019,07,01);
		LocalDate f8 = LocalDate.of(2019,8,01);
		LocalDate f9 = LocalDate.of(2019,9,01);
		LocalDate f10 = LocalDate.of(2019,10,01);
		LocalDate f11 = LocalDate.of(2019,11,01);
		LocalDate f12 = LocalDate.of(2019,12,01);	
		ArrayList<LocalDate> listaFechas = new ArrayList<LocalDate>();

		listaFechas.add(f1);
		listaFechas.add(f2);
		listaFechas.add(f3);
		listaFechas.add(f4);
		//listaFechas.add(f5);
		//listaFechas.add(f6);
		//listaFechas.add(f7);
		//listaFechas.add(f8);
		//listaFechas.add(f9);
		//listaFechas.add(f10);
		//listaFechas.add(f11);
		//listaFechas.add(f12);

		periodo.setFechas(listaFechas);

	}

	public ArrayList<LocalDate> verificarFaltaFechas(LocalDate f1, LocalDate f2, ArrayList<LocalDate> lista, ArrayList<LocalDate> listaFechas, int i, int j ,LocalDate fechaCre){
		i=i+1;
		//System.out.println("iteracion:" + i + " J: " + j);
		//System.out.println("f1:" + f1 + " f2:" + f2);	
		//System.out.println("fechaCre:" + fechaCre );
		//System.out.println("listaFechas:" + listaFechas );

		if ( f1.equals( fechaCre)) {
			//System.out.println("****mismo mes: "+fechaCre);
			if(!buscarEnListaExistentes(f1, listaFechas)) {
				lista.add(fechaCre);
			}
		}
		if(f1.equals( f2)) {
			if (i>=1) {
				if(!buscarEnListaExistentes(f1, listaFechas)) {
					//System.out.println("****mismo mes: "+f1);
					lista.add(f1);
				}
			}

		}else	
			if(f1.plusMonths(1).equals( f2)) {
				//lista.add(f1.plusMonths(1));	
				//System.out.println("primer mes es anterior al mes siguiente");
			} else
				if(!f1.plusMonths(2).equals( f2)) {
					//System.out.println("****agrega el mes " + f1.plusMonths(1));
					if(!buscarEnListaExistentes(f1.plusMonths(1), listaFechas)) {
						lista.add(f1.plusMonths(1));
					}
					//System.out.println("condicion 3");
					verificarFaltaFechas(f1.plusMonths(1),f2,lista,listaFechas,i++,j,fechaCre);	
					//System.out.println("condicion 31");
				}else {
					//System.out.println("condifiocn 4 " );
					verificarFaltaFechas(f1.plusMonths(1),f2.plusMonths(-1),lista,listaFechas,i++,j,fechaCre);	
					//System.out.println("condifiocn 41 ");
				}
		return lista;
	}	

	public boolean buscarEnListaExistentes(LocalDate f1, ArrayList<LocalDate> listaFechas ) {

		Iterator<LocalDate> it = listaFechas.iterator();		
		while (it.hasNext()) {
			LocalDate lf = it.next();
			//System.out.println(f1 + "-" + lf);
			if(f1.equals(lf)) {
				return true;
			}
		}	
		return false;
	}
}
