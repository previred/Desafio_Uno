package cl.previred.desafio.service;

import cl.previred.desafio.component.Period;
import cl.previred.desafio.component.Util;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

@Service("periodService")
public class PeriodServiceImpl implements IPeriodService {

    @Autowired
    Environment env;

    @Autowired
    @Qualifier("period")
    private Period period;

    @Autowired
    @Qualifier("util")
    private Util util;

    @Override
    public Period getAllPeriods() {
        return getMissingPeriods();
    }

    private Period getMissingPeriods() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response = rest.exchange(env.getProperty("URL"), HttpMethod.GET, entity, String.class);

            JSONObject jsonRsp = new JSONObject(response.getBody());
            JSONArray arrayFechas = new JSONArray(jsonRsp.get("fechas").toString());
            period.setId((Integer) jsonRsp.get("id"));
            period.setFechaCreacion(util.convertStringToDate(jsonRsp.get("fechaCreacion").toString()));
            period.setFechaFin(util.convertStringToDate(jsonRsp.get("fechaFin").toString()));
            period.setFechas(util.convertJsonArrayToList(arrayFechas));
            period.setFechasFaltantes(getPeriods(period, arrayFechas));
        } catch (Exception e) {
            return period;
        }

        return period;
    }

    public List<String> getPeriods(Period period, JSONArray arrayFechas) throws JSONException {
        List<Date>allDates = util.getAllPeriodsInRange(period.getFechaCreacion(), period.getFechaFin());
        List<Date>allIncommingDates = util.getAllPeriodsInDateList(arrayFechas);
        List<Date> differences = allDates.stream().filter(aObject -> !allIncommingDates.contains(aObject)).collect(Collectors.toList());

        return util.convertDateArrayToList(differences);
    }

}
