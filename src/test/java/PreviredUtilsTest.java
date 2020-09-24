import com.previred.desafio.MsDesafioApplication;
import com.previred.desafio.model.DataGeneratorResponse;
import com.previred.desafio.util.PreviredUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MsDesafioApplication.class)
@AutoConfigureMockMvc
public class PreviredUtilsTest {

    @Test
    public void getLimitDatesTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse("2000-01-01", formatter);
        LocalDate endDate = LocalDate.parse("2020-01-01", formatter);
        DataGeneratorResponse dataGeneratorResponse = new DataGeneratorResponse();
        PreviredUtils.getLimitDates(dataGeneratorResponse, startDate, endDate);
        assertEquals(true, dataGeneratorResponse.getStartDate().isBefore(endDate));
        assertEquals(true, dataGeneratorResponse.getEndDate().isAfter(startDate));
    }

    @Test
    public void getRandomDatesTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse("2000-01-01", formatter);
        LocalDate endDate = LocalDate.parse("2020-01-01", formatter);
        DataGeneratorResponse dataGeneratorResponse = new DataGeneratorResponse();
        PreviredUtils.getLimitDates(dataGeneratorResponse, startDate, endDate);
        PreviredUtils.getRandomDates(dataGeneratorResponse);
        AtomicReference<Boolean> validateConsistency = new AtomicReference(true);
        dataGeneratorResponse.getDateList().forEach(localDate -> {
            if(localDate.isBefore(dataGeneratorResponse.getStartDate()) || localDate.isAfter(dataGeneratorResponse.getEndDate())){
                validateConsistency.set(false);
            }
        });
        assertEquals(true, validateConsistency.get());
    }

    @Test
    public void getRandomDatesTest_fail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse("2000-01-01", formatter);
        LocalDate endDate = LocalDate.parse("2020-01-01", formatter);
        DataGeneratorResponse dataGeneratorResponse = new DataGeneratorResponse();
        PreviredUtils.getLimitDates(dataGeneratorResponse, startDate, endDate);
        PreviredUtils.getRandomDates(dataGeneratorResponse);
        // add one out of range to force inconsistency
        dataGeneratorResponse.getDateList().add(LocalDate.parse("2030-01-01", formatter));
        AtomicReference<Boolean> validateConsistency = new AtomicReference(true);
        dataGeneratorResponse.getDateList().forEach(localDate -> {
            if(localDate.isBefore(dataGeneratorResponse.getStartDate()) || localDate.isAfter(dataGeneratorResponse.getEndDate())){
                validateConsistency.set(false);
            }
        });
        assertEquals(false, validateConsistency.get());
    }

    @Test
    public void fillMissingDatesTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse("2000-01-01", formatter);
        LocalDate endDate = LocalDate.parse("2020-01-01", formatter);
        DataGeneratorResponse dataGeneratorResponse = new DataGeneratorResponse();
        PreviredUtils.getLimitDates(dataGeneratorResponse, startDate, endDate);
        PreviredUtils.getRandomDates(dataGeneratorResponse);
        PreviredUtils.fillMissingDates(dataGeneratorResponse);
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
    }



}
