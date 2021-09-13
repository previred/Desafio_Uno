package cl.previred.gdd.services;

import cl.previred.gdd.dto.GddRequest;
import cl.previred.gdd.dto.GddResponse;
import cl.previred.gdd.services.impl.GddService;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class GddServiceTest {

  @Test
  public void t1_listOk() throws ServiceException {
    GddRequest req = new GddRequest();
    req.setFechaCreacion("1969-03-01");
    req.setFechaFin("1970-01-01");
    req.setId(1);

    HashSet<String> set = new HashSet<>();
    set.add("1969-03-01");
    set.add("1969-05-01");
    set.add("1969-09-01");
    set.add("1970-01-01");
    req.setFechas(set);

    String[] a = { "1969-04-01",
                    "1969-06-01",
                    "1969-07-01",
                    "1969-08-01",
                    "1969-10-01",
                    "1969-11-01",
                    "1969-12-01"};
    GddService service = new GddService();
    GddResponse resp = service.calculateMissingPeriods(req);

    assertIterableEquals(Arrays.asList(a), resp.getFechasFaltantes());
  }

  @Test
  public void t1_Size() throws ServiceException {
    GddRequest req = new GddRequest();
    req.setFechaCreacion("1900-03-01");
    req.setFechaFin("2000-01-01");
    req.setId(1);

    HashSet<String> set = new HashSet<>();
    set.add("1969-03-01");
    set.add("1969-05-01");
    set.add("1969-09-01");
    set.add("1970-01-01");
    req.setFechas(set);

    String[] a = { "1969-04-01",
            "1969-06-01",
            "1969-07-01",
            "1969-08-01",
            "1969-10-01",
            "1969-11-01",
            "1969-12-01"};
    GddService service = new GddService();
    GddResponse resp = service.calculateMissingPeriods(req);

    assertEquals(100, resp.getFechasFaltantes().size());
  }
}
