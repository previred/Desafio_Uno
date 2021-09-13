package cl.previred.gdd.services.impl;

import cl.previred.gdd.dto.GddRequest;
import cl.previred.gdd.dto.GddResponse;
import cl.previred.gdd.services.IGddService;
import cl.previred.gdd.services.ServiceException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que implementa interface IGddService
 */
@Service
public class GddService implements IGddService {

  private static final String FORMAT_DATE = "yyyy-MM-dd";

  private static final int LIMIT = 100;

  /**
   * Implementa el método calculateMissingPeriods
   * @param request
   * @return
   * @throws ServiceException
   */
  public GddResponse calculateMissingPeriods(GddRequest request) throws ServiceException {
    SimpleDateFormat sdf;
    Date creationDate;
    Date endDate;
    try {
      sdf = new SimpleDateFormat(FORMAT_DATE);
      creationDate = sdf.parse(request.getFechaCreacion());
      endDate = sdf.parse(request.getFechaFin());
    } catch (ParseException e) {
      throw new ServiceException("Invalid format date.", e);
    }
    GregorianCalendar initialCalendar = new GregorianCalendar();
    initialCalendar.setTime(creationDate);

    GregorianCalendar finalCalendar = new GregorianCalendar();
    finalCalendar.setTime(endDate);

    ArrayList<String> notFoundDates = new ArrayList<>();
    while (initialCalendar.getTime().compareTo(finalCalendar.getTime()) <= 0) {
      String fDate = sdf.format(initialCalendar.getTime());
      if (!request.getFechas().contains(fDate) && notFoundDates.size() < LIMIT) {
        notFoundDates.add(fDate);
      }
      initialCalendar.add(GregorianCalendar.MONTH, 1);
    }

    GddResponse response = new GddResponse();
    response.setFechaCreacion(request.getFechaCreacion());
    response.setFechaFin(request.getFechaFin());
    response.setId(request.getId());
    response.setFechasFaltantes(notFoundDates);
    return response;
  }

}
