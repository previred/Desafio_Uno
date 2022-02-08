package martinmgutierrezb.periodos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import martinmgutierrezb.periodos.dao.IPeriodoDao;
import martinmgutierrezb.periodos.models.Periodo;
import martinmgutierrezb.periodos.service.IPeriodoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class SpringbootApirestApplicationTests {
	
	@Autowired
	IPeriodoDao periodoDao;
	@Autowired
	IPeriodoService periodoService;
	@Test
	@Ignore
	public void pruebaServicioPeriodo() {
		Periodo periodo = periodoDao.getPeriodos();
		assertNotNull(periodo);
		assertNotNull(periodo.getFechaCreacion());
		assertNotNull(periodo.getFechaFin());
		assertNotNull(periodo.getFechas());
		assertTrue(periodo.getFechas().size()>=90 && periodo.getFechas().size()<=100);
	}
	
	@Test
	@Ignore
	public void pruebaFechasFaltantes() {
		Periodo periodo = periodoService.getPeriodosFaltantes();
		assertNotNull(periodo);
		assertNotNull(periodo.getFechaCreacion());
		assertNotNull(periodo.getFechaFin());
		assertNotNull(periodo.getFechas());
		assertTrue(periodo.getFechas().size()>=90 && periodo.getFechas().size()<=100);
		assertNotNull(periodo.getFechasFaltantes());
		assertTrue(periodo.getFechasFaltantes().size()>0);
		int cantidadPeriodosTotales = getCantidadPeriodosTotales(periodo.getFechaCreacion(), periodo.getFechaFin());
		assertTrue(periodo.getFechas().size()+periodo.getFechasFaltantes().size()==cantidadPeriodosTotales);
		
	}

	private int getCantidadPeriodosTotales(LocalDate fechaCreacion, LocalDate fechaFin) {
		int result = 0;
		if(fechaFin.getYear()-fechaCreacion.getYear()>1) {
			result += 12*((fechaFin.getYear()-fechaCreacion.getYear())-1);
			result += getCantidadPeriodoExtremoInferior(fechaCreacion);
			result += fechaFin.getMonthValue();
		}else if(fechaFin.getYear()-fechaCreacion.getYear()==1) {
			result += getCantidadPeriodoExtremoInferior(fechaCreacion);
			result += fechaFin.getMonthValue();
		}else {
			result += fechaFin.getMonthValue()-fechaCreacion.getMonthValue()+1;
		}
		return result;
	}

	private int getCantidadPeriodoExtremoInferior(LocalDate fechaCreacion) {
		return 12-(fechaCreacion.getMonthValue()-1);
	}

}
