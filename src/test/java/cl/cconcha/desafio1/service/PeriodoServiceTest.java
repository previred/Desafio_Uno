package cl.cconcha.desafio1.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import cl.cconcha.desafio1.data.PeriodoData;
import cl.cconcha.desafio1.domain.Periodo;

@RunWith(MockitoJUnitRunner.class)
public class PeriodoServiceTest {
	
	@InjectMocks
	private PeriodoServiceImpl periodoService;
	@Mock
	private RestTemplate restTemplate;
	
	@Before
	public void setUp() {
		ReflectionTestUtils.setField(periodoService, "gddUrl", "some-url");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(),Mockito.eq(Periodo.class))).thenReturn(PeriodoData.getPeriodos());
	}
	
	@Test
	public void fillPeriodosTest() {
		
		Periodo periodo = periodoService.fillPeriodos();
		
		List<LocalDate> fechasFaltantes = new ArrayList<LocalDate>();
		fechasFaltantes.add(LocalDate.of(1993, Month.NOVEMBER, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.JANUARY, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.APRIL, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.MAY, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.JULY, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.SEPTEMBER, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.OCTOBER, 1));
		fechasFaltantes.add(LocalDate.of(1994, Month.DECEMBER, 1));
		fechasFaltantes.add(LocalDate.of(1995, Month.FEBRUARY, 1));
		
		Assert.assertEquals(periodo.getFechasFaltantes(), fechasFaltantes);
	}
}
