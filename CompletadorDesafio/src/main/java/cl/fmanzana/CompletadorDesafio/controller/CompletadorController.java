package cl.fmanzana.CompletadorDesafio.controller;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import cl.fmanzana.CompletadorDesafio.dao.CompletadorDAO;
import cl.fmanzana.CompletadorDesafio.dao.impl.CompletadorDAOImpl;
import cl.fmanzana.CompletadorDesafio.dto.PeriodosGDD;
import cl.fmanzana.CompletadorDesafio.dto.PeriodosCompletosDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CompletadorController", description = "Desafio 1")
@RestController
public class CompletadorController {
	
	@ApiOperation(value = "Prueba servicio")
	@RequestMapping(method = RequestMethod.GET, value = "/prueba" )
    public String prueba() {
        return "Servicios Operativos.";
    }
	
	@ApiOperation(value = "Ejercicio desafio 1")
	@RequestMapping(method = RequestMethod.GET, value = "/completaFechas")
    public String completaFechas() throws Exception {
		CompletadorDAO completadorDAO = new CompletadorDAOImpl();
		
		String resp = completadorDAO.obtienePeriodo();
		
		Gson g = new Gson();
		PeriodosGDD periodosGDD = g.fromJson(resp, PeriodosGDD.class);

		String[] periodosFull = generaFechas(periodosGDD.getFechaCreacion(), periodosGDD.getFechaFin());
		
		String[] faltantes = generaFaltantes(periodosGDD.getFechas(),periodosFull);
		
		
		PeriodosCompletosDTO periodosCompletosDTO = new PeriodosCompletosDTO();
		
		periodosCompletosDTO.setId(periodosGDD.getId());
		periodosCompletosDTO.setFechaCreacion(periodosGDD.getFechaCreacion());
		periodosCompletosDTO.setFechaFin(periodosGDD.getFechaFin());
		periodosCompletosDTO.setFechas(periodosGDD.getFechas());
		periodosCompletosDTO.setFechasFaltantes(faltantes);
		
		String str = g.toJson(periodosCompletosDTO);
		resp = str;
		
		
        return resp;
    }
	
	private String[] generaFechas(String fechaInicio, String fechaFin) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int anoIni = Integer.valueOf(fechaInicio.substring(0, 4)).intValue();
		int anoFin = Integer.valueOf(fechaFin.substring(0, 4)).intValue();
		int mesIni = Integer.valueOf(fechaInicio.substring(5, 7)).intValue();
		int mesFin = Integer.valueOf(fechaFin.substring(5, 7)).intValue();
		ArrayList<String> resp = new ArrayList<String>();
				
		for (int ano = anoIni; ano <= anoFin; ano++) {
			if (ano == anoIni) {
				for (int mes = mesIni; mes <= 12; mes++) {
					String fecha = String.valueOf(ano) + "-" + StringUtils.leftPad(String.valueOf(mes), 2, "0") + "-01";
					resp.add(fecha);
					if (ano == anoFin && mes == mesFin) break;
				}
			}else if (ano == anoFin) {
				for (int mes = 1; mes <= mesFin; mes++) {
					String fecha = String.valueOf(ano) + "-" + StringUtils.leftPad(String.valueOf(mes), 2, "0") + "-01";
					resp.add(fecha);
					if (ano == anoFin && mes == mesFin) break;
				}
			}else {
				for (int mes = 1; mes <= 12; mes++) {
					String fecha = String.valueOf(ano) + "-" + StringUtils.leftPad(String.valueOf(mes), 2, "0") + "-01";
					resp.add(fecha);
					if (ano == anoFin && mes == mesFin) break;
				}
			}
		}
		
		String str[] = new String[resp.size()]; 
		  
        for (int j = 0; j < resp.size(); j++) { 
            str[j] = resp.get(j); 
        } 
		
		return str;
	}
	
	private String[] generaFaltantes(String[] fechas, String[] todas) {
		ArrayList<String> resp = new ArrayList<String>();
		for (int i = 0; i < todas.length; i++) {
			boolean encontrado = false;
			
			for (int j = 0; j < fechas.length; j++) {
				if (todas[i].equalsIgnoreCase(fechas[j])) {
					encontrado = true;
				}
			}
			
			
			if (!encontrado) {
				resp.add(todas[i]);
			}
		}
		
		String str[] = new String[resp.size()]; 
		  
        for (int j = 0; j < resp.size(); j++) { 
            str[j] = resp.get(j); 
        } 
		
		return str;
	}
}


