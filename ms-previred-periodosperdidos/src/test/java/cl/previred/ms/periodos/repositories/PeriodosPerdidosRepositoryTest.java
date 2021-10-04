package cl.previred.ms.periodos.repositories;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import cl.previred.ms.periodos.exceptions.PreviredException;
import cl.previred.ms.periodos.restclient.PeriodosPerdidosClient;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:rest.properties")
public class PeriodosPerdidosRepositoryTest {
	  @Mock private PeriodosPerdidosClient periodosPerdidosClient;
	  @InjectMocks private PeriodosPerdidosRepository periodosPerdidosRepository;

	  @Test
	  public void shouldGetError_whenRegistrarCambios() throws Exception {
	    doThrow(new IllegalArgumentException()).when(periodosPerdidosClient).periodos();
	    PreviredException ex =
	        assertThrows(
	        		PreviredException.class,
	            () -> periodosPerdidosRepository.periodosPerdidos());
	    assertTrue(ex.getMessage().contains("Error invocando API de Periodos"));
	  }

}
