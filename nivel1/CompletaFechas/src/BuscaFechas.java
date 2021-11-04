import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class BuscaFechas {
	static Gson v_gson = new Gson();
	
	public static void main(String[] args) {
		
		Entrada ent = leerEntrada();
		Respuesta resp = buscarFechas(ent);
		System.out.println(v_gson.toJson(resp));
	}

	private static Respuesta buscarFechas(Entrada in) {
		Date vfin = fechaToDate(in.getFechaFin());
		List<String> fvacias = new ArrayList<String>();
		List<String> lFechas = Arrays.asList(in.getFechas());
		for (Date vini = fechaToDate(in.getFechaCreacion()); !vini.after(vfin); vini=sumarMes(vini)) {
			if ( !lFechas.contains(fechaConFormato(vini)) ) {
				fvacias.add(fechaConFormato(vini));
		//		System.out.println(fechaConFormato(vini));
			} 
			
		}
		Respuesta vc = new Respuesta(in.getId(), in.getFechaCreacion(), in.getFechaFin(), fvacias.toArray(new String[fvacias.size()]));
		return vc;
	}

	private static Entrada leerEntrada() {
		Entrada v_in = null;
		try {
			Scanner reader = new Scanner(new InputStreamReader(System.in));
			StringBuffer buffer = new StringBuffer();
			while (reader.hasNextLine()) {
				buffer.append(reader.nextLine());
			}
			v_in = v_gson.fromJson(buffer.toString(), Entrada.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v_in;
	}
	
	public static Date sumarMes(Date vd) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(vd);
	    calendar.add(Calendar.MONTH, 1);
	    return calendar.getTime();
	}
	
	public static String fechaConFormato(Date fi) {
		String resp="";
		resp = new SimpleDateFormat("yyyy-MM-dd").format(fi);
		return resp;
	}
	
	public static Date fechaToDate(String vfecha) {
		Date f_d =null;
		try {
			f_d =  new SimpleDateFormat("yyyy-MM-dd").parse(vfecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return f_d;
	}

}
