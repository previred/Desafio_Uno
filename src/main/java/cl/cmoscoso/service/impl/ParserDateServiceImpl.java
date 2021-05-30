package cl.cmoscoso.service.impl;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.cmoscoso.dao.PeriodosDAO;
import cl.cmoscoso.dto.DatesParsedDTO;
import cl.cmoscoso.entity.PeriodosEntity;
import cl.cmoscoso.service.ParserDateService;

@Service
public class ParserDateServiceImpl implements ParserDateService {
	@Autowired
	private PeriodosDAO periodosDao;

	@Autowired
	private ObjectMapper om;

	@Override
	public DatesParsedDTO getParseDates() throws RestClientException, URISyntaxException {
		PeriodosEntity response = periodosDao.getPeriodos();

		DatesParsedDTO out = om.convertValue(response, DatesParsedDTO.class);

		LocalDate start = out.getFechaCreacion();
		LocalDate finish = out.getFechaFin();

		for (LocalDate i = start; i.isBefore(finish) || i.isEqual(finish); i = i.plusMonths(1)) {
			if (!response.getFechas().contains(i)) {
				out.getFechasFaltantes().add(i.toString());
			}
		}

		return out;
	}

}
