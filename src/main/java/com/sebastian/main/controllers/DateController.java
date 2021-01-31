package com.sebastian.main.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sebastian.main.utils.HttpServices;

@Controller
public class DateController {

	@RequestMapping(value = "/test", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCategorys() {

		String response = "";

		try {
			response = new HttpServices().getStringResponse("http://127.0.0.1:8080/periodos/api");
			JSONObject jsonObject = new JSONObject(response);

			ArrayList<String> fechas = new ArrayList<String>();
			ArrayList<String> fechasFaltantes = new ArrayList<String>();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaInicio = sdf.parse(jsonObject.getString("fechaCreacion"));
			Date fechaFin = sdf.parse(jsonObject.getString("fechaFin"));

			JSONArray fechasDatos = jsonObject.getJSONArray("fechas");

			for (int i = 0; i < fechasDatos.length(); i++) {
				fechas.add(fechasDatos.get(i).toString());
			}

			Date currentDate = fechaInicio;

			while (currentDate.compareTo(fechaFin) <= 0) {

				String date = sdf.format(currentDate);
				if (!fechas.contains(date)) {
					fechasFaltantes.add(date);
				}

				final java.util.Calendar cal = GregorianCalendar.getInstance();
				cal.setTime(currentDate);
				cal.add(GregorianCalendar.MONTH, +1);

				currentDate = cal.getTime();

			}
			jsonObject.put("fechasFaltantes", fechasFaltantes);
			response = jsonObject.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
