package com.previred.desafio.missingdates.connector;

import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.previred.desafio.missingdates.model.DateInfo;
import com.previred.desafio.missingdates.resource.DateGeneratorConnectorResource;

@Service
public class DateGeneratorConnector {

	private static DateInfo response;
	private static List<LocalDate> missingDates = new ArrayList<LocalDate>();

	private LocalDate date;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private HttpEntity<String> entity;
	private JSONObject dateGeneratorResponseJson;

	/** Logger instance. */
	private final static Logger LOGGER = LoggerFactory.getLogger(DateGeneratorConnector.class);

	/** This url could be on a .properties or .yml file. */
	private static final String url = "http://127.0.0.1:8080/periodos/api/";

	/**
	 * @param none.
	 * @return DateInfo.class Object.
	 */
	public DateInfo getDateInfo() {
		missingDates.removeAll(missingDates);
		RestTemplate dateResourceRestTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/json");
		try {
			entity = new HttpEntity<String>(headers);
			String dateGeneratorResponse = dateResourceRestTemplate.exchange(url, HttpMethod.GET, entity, String.class)
					.getBody();
			setResponseData(dateGeneratorResponse);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}
		return response;
	}

	public void setResponseData(String dateGeneratorResponse) throws ParseException {
		dateGeneratorResponseJson = new JSONObject(dateGeneratorResponse);
		response = new DateInfo();
		response.setId(dateGeneratorResponseJson.optString("id"));
		date = LocalDate.parse(dateGeneratorResponseJson.optString("fechaCreacion"), dateFormat);
		response.setFechaCreacion(date);
		date = LocalDate.parse(dateGeneratorResponseJson.optString("fechaFin"), dateFormat);
		response.setFechaFin(date);
		response.setFechas(setDateList(dateGeneratorResponseJson.getJSONArray("fechas")));
		// setMissingDates(response.getFechas());
		Map<Integer, ArrayList<LocalDate>> dates = setMissingDates(response.getFechas());
		response.setFechasFaltantes(getMissingDates(dates));
	}

	/**
	 * @param dates
	 */
	private List<LocalDate> getMissingDates(Map<Integer, ArrayList<LocalDate>> dates) {
		List<LocalDate> missingDates = new ArrayList<LocalDate>();
		String createdMonths = "";
		dates.forEach((k, v) -> {
			LocalDate tempDate;
			LocalDate missingDate;
			for (int i = 1; i <= 12; i++) {
				for (LocalDate date : v) {
					if (date.getMonthValue() == i) {
						tempDate = LocalDate.parse(date.withDayOfMonth(25).toString(), dateFormat);
						v.add(tempDate);
						v.remove(date);
						break;
					} else if (date.getDayOfMonth() != 25) {
						missingDate = LocalDate.parse(date.withMonth(i).toString(), dateFormat);
						missingDates.add(missingDate);
						break;
					}
				}
			}
		});
		return missingDates;
	}

	public List<LocalDate> setDateList(JSONArray dateArrayJson) throws ParseException {
		String date;
		List<LocalDate> dateList = new ArrayList<LocalDate>();
		for (Object obj : dateArrayJson) {
			date = (String) obj;
			this.date = LocalDate.parse(date, dateFormat);
			dateList.add(this.date);
		}
		return dateList;
	}

	public Map<Integer, ArrayList<LocalDate>> setMissingDates(List<LocalDate> dates) {
		Map<Integer, ArrayList<LocalDate>> datesByYear = new HashMap<Integer, ArrayList<LocalDate>>();
		ArrayList<LocalDate> tempMissingDates = new ArrayList<LocalDate>();
		Integer year = dates.get(0).getYear();
		for (LocalDate date : dates) {
			if (date.getYear() == year) {
				tempMissingDates.add(date);
			} else {
				datesByYear.put(year, tempMissingDates);
				tempMissingDates = new ArrayList<LocalDate>();
				tempMissingDates.add(date);
				year = date.getYear();
			}
		}
		return datesByYear;
	}

}
