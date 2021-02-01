package com.previred.GDD.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.previred.GDD.models.Periodo;
import com.previred.GDD.models.PeriodoServicio;
import com.previred.GDD.utils.UtilFechas;

@Controller
public class ControllerGdd {

	private Periodo periodo = new Periodo();

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "Generador de fechas");
		return "index";
	}

	@PostMapping("/calculaFechas")
	public String calculaFechas(Model model, @RequestParam("fechaInicio") String fechaInicio,
			@RequestParam("fechaFinal") String fechaFinal, RedirectAttributes redirect) {

		UtilFechas utilFechas = new UtilFechas();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			LocalDate fechaUno = utilFechas.stringToLocalDate(fechaInicio);
			LocalDate fechaDos = utilFechas.stringToLocalDate(fechaFinal);

			int meses = utilFechas.diferenciaMeses(fechaInicio, fechaFinal);

			if (fechaUno.getDayOfMonth() != 1 || fechaDos.getDayOfMonth() != 1) {
				redirect.addFlashAttribute("mens", "Las fechas seleccionadas deben ser el primer día del mes!");
			} else if (fechaUno.isEqual(fechaDos)) {
				redirect.addFlashAttribute("mens", "Las fechas seleccionadas no deben ser iguales!");
			} else if (fechaDos.isBefore(fechaUno)) {
				redirect.addFlashAttribute("mens", "Las fecha FINAL debe ser posterior a la fecha INICIO");
			} else if (meses < 10 || meses > 100) {
				redirect.addFlashAttribute("mens",
						"El rango de fechas no debe ser menor que 10 ni mayor que 100 meses!");
			} else {
				List<Date> rangoFechas = utilFechas.rangoFechas(fechaInicio, meses);
				List<Date> fechasAleatorias = utilFechas.fechasAleatotias(rangoFechas, 6);
				List<Date> fechasRestantes = utilFechas.fechasFaltantes(rangoFechas, fechasAleatorias);


				this.periodo = new Periodo(1, fechaInicio, fechaFinal, utilFechas.datesToString(fechasAleatorias),
						utilFechas.datesToString(fechasRestantes));

				model.addAttribute("periodo", periodo);
				model.addAttribute("titulo", "Período de fechas");

				return "vistaPeriodo";
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}

	// devuelve JSON con toda la informacion a partir de la vista
	@GetMapping("/fechasJSON")
	@ResponseBody
	public Periodo periodoRest() {

		return this.periodo;
	}

	// devuelve JSON con toda la informacion a partir de servicio GDD JSON url
	@GetMapping("/respuestaDesdeServicioGDD")
	public String periodoRestJSON(Model model) throws JSONException, IOException, ParseException {

		JSONObject json = readJsonFromUrl("http://localhost:8080/servicioGDD");

		UtilFechas utilFechas = new UtilFechas();

		List<Date> fechasAleatorias = new ArrayList<Date>();
		JSONArray jsonArray = json.getJSONArray("fechas");
		for (int i = 0; i < jsonArray.length(); i++) {
			fechasAleatorias.add(new SimpleDateFormat("yyyy-MM-dd").parse(jsonArray.getString(i)));
		}

		int id = json.getInt("id");
		String fechaCreacion = json.getString("fechaCreacion");
		String fechaFin = json.getString("fechaFin");

		int meses = utilFechas.diferenciaMeses(fechaCreacion, fechaFin);
		List<Date> rangoFechas = utilFechas.rangoFechas(fechaCreacion, meses);
		List<Date> fechasRestantes = rangoFechas;

		for (int i = 0; i < rangoFechas.size(); i++) {
			for (int j = 0; j < fechasAleatorias.size(); j++) {
				if (rangoFechas.get(i).equals(fechasAleatorias.get(j))) {
					fechasRestantes.remove(i);
					
				}
			}
		}

		
		this.periodo = new Periodo(id, fechaCreacion, fechaFin, 
				utilFechas.datesToString(fechasAleatorias), utilFechas.datesToString(fechasRestantes));
		
		
		model.addAttribute("periodo", periodo);
		model.addAttribute("titulo", "Período de fechas desde  JSON GDD");

		return "vistaPeriodo";
	}

	// metodo para generar JSON de prueba Servicio GDD
	@GetMapping("/servicioGDD")
	@ResponseBody
	public PeriodoServicio servicioGDD() throws ParseException {

		UtilFechas utilFechas = new UtilFechas();
		int meses = utilFechas.diferenciaMeses("2021-01-01", "2021-10-01");
		List<Date> rangoFechas = utilFechas.rangoFechas("2021-01-01", meses);
		List<Date> fechasAleatorias = utilFechas.fechasAleatotias(rangoFechas, 4);

		PeriodoServicio periodoServicio = new PeriodoServicio(1, "2021-01-01", "2021-10-01",
				utilFechas.datesToString(fechasAleatorias));

		return periodoServicio;
	}

	// ******************************* Para consumir JSON desde url***************************//

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

}
