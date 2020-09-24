import com.previred.desafio.MsDesafioApplication;
import com.previred.desafio.controller.DataGeneratorController;
import com.previred.desafio.model.DataGeneratorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MsDesafioApplication.class)
@AutoConfigureMockMvc
public class dataGeneratorControllerTest {

    @Autowired
    private DataGeneratorController dataGeneratorController;

    @Mock
    private ServletOutputStream servletOutputStream;

    @Test
    public void dataGeneratorTest() {
        DataGeneratorResponse dataGeneratorResponse = dataGeneratorController.generator();
        assertEquals(true, dataGeneratorResponse.getEndDate().isAfter(dataGeneratorResponse.getStartDate()));
        assertEquals(true, !dataGeneratorResponse.getDateList().isEmpty());
    }

    @Test
    public void level1Test() {
        DataGeneratorResponse dataGeneratorResponse = dataGeneratorController.level1(dataGeneratorController.generator());
        AtomicReference<Boolean> validateConsistency = new AtomicReference(true);
        dataGeneratorResponse.getDateList().forEach(localDate -> {
            if (dataGeneratorResponse.getMissingDatesList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        dataGeneratorResponse.getMissingDatesList().forEach(localDate -> {
            if (dataGeneratorResponse.getDateList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        assertEquals(true, validateConsistency.get());
        assertEquals(true, dataGeneratorResponse.getEndDate().isAfter(dataGeneratorResponse.getStartDate()));
        assertEquals(true, !dataGeneratorResponse.getDateList().isEmpty());
        assertEquals(true, !dataGeneratorResponse.getMissingDatesList().isEmpty());
    }

    @Test
    public void level1Test_fail() {
        DataGeneratorResponse dataGeneratorResponse = dataGeneratorController.level1(dataGeneratorController.generator());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //same date is added to both lists to generate inconsistency
        dataGeneratorResponse.getDateList().add(LocalDate.parse("2000-01-01", formatter));
        dataGeneratorResponse.getMissingDatesList().add(LocalDate.parse("2000-01-01", formatter));
        AtomicReference<Boolean> validateConsistency = new AtomicReference(true);
        dataGeneratorResponse.getDateList().forEach(localDate -> {
            if (dataGeneratorResponse.getMissingDatesList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        dataGeneratorResponse.getMissingDatesList().forEach(localDate -> {
            if (dataGeneratorResponse.getDateList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        assertEquals(false, validateConsistency.get());
    }

    @Test
    public void level2Test() throws IOException {
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        when(mockedResponse.getOutputStream()).thenReturn(servletOutputStream);
        dataGeneratorController.level2(mockedResponse);
        assertNotNull(mockedResponse);
    }

    @Test
    public void level3Test() {
        DataGeneratorResponse dataGeneratorResponse = dataGeneratorController.level3();
        AtomicReference<Boolean> validateConsistency = new AtomicReference(true);
        dataGeneratorResponse.getDateList().forEach(localDate -> {
            if (dataGeneratorResponse.getMissingDatesList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        dataGeneratorResponse.getMissingDatesList().forEach(localDate -> {
            if (dataGeneratorResponse.getDateList().contains(localDate)) {
                validateConsistency.set(false);
            }
        });
        assertEquals(true, validateConsistency.get());
        assertEquals(true, dataGeneratorResponse.getEndDate().isAfter(dataGeneratorResponse.getStartDate()));
        assertEquals(true, !dataGeneratorResponse.getDateList().isEmpty());
        assertEquals(true, !dataGeneratorResponse.getMissingDatesList().isEmpty());
    }

}
