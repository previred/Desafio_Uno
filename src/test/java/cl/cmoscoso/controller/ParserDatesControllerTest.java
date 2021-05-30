package cl.cmoscoso.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.cmoscoso.config.exceptions.PeriodosServiceException;
import cl.cmoscoso.dao.PeriodosDAO;
import cl.cmoscoso.dto.DatesParsedDTO;
import cl.cmoscoso.entity.PeriodosEntity;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ParserDatesControllerTest {
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private MockMvc mvc;

	@MockBean
	PeriodosDAO dao;

	@Autowired
	private ObjectMapper om;

	@Test
	public void test1Test() throws Exception {
		PeriodosEntity periodos = getPeriodos(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 9, 1));

		given(this.dao.getPeriodos()).willReturn(periodos);

		ResultActions result = this.mvc//
				.perform(get("/api/cmoscoso/parseDate/1.0/get")//
						.accept(MediaType.APPLICATION_JSON_VALUE)//
						.contentType(MediaType.APPLICATION_JSON_VALUE))//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.id").value("1"))//
				.andExpect(jsonPath("$.fechaCreacion").value("2021-05-01"))
				.andExpect(jsonPath("$.fechaFin").value("2021-09-01"));
		String jsonResult = result.andReturn().getResponse().getContentAsString();

		DatesParsedDTO parsed = om.readValue(jsonResult, DatesParsedDTO.class);
		log.debug(parsed.toString());
		Set<String> faltantes = parsed.getFechasFaltantes();

		boolean valid = findIn(faltantes, createDates(aMonth(2021, 7), aMonth(2021, 8), aMonth(2021, 9)));

		assertTrue(valid);
	}

	@Test
	public void test2Test() throws Exception {
		given(this.dao.getPeriodos()).willThrow(PeriodosServiceException.class);

		this.mvc//
				.perform(get("/api/cmoscoso/parseDate/1.0/get")//
						.accept(MediaType.APPLICATION_JSON_VALUE)//
						.contentType(MediaType.APPLICATION_JSON_VALUE))//
				.andExpect(status().is5xxServerError());
	}

	private boolean findIn(Set<String> faltantes, Set<LocalDate> createDates) {
		boolean out = true;
		for (String falta : faltantes) {
			if (!createDates.contains(toLocalDate(falta))) {
				out = false;
				break;
			}
		}
		return out;

	}

	private LocalDate toLocalDate(String date) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	private PeriodosEntity getPeriodos(LocalDate start, LocalDate finish) {
		PeriodosEntity out = new PeriodosEntity();
		out.setId(1);
		out.setFechaCreacion(start);
		out.setFechaFin(finish);

		out.setFechas(createDates(aMonth(2021, 5), aMonth(2021, 6)));

		return out;
	}

	private LocalDate aMonth(int yeard, int month) {
		return LocalDate.of(yeard, month, 1);
	}

	private Set<LocalDate> createDates(LocalDate... dates) {
		Set<LocalDate> out = new HashSet<LocalDate>(dates.length);
		for (LocalDate ld : dates) {
			out.add(ld);
		}
		return out;
	}

}
