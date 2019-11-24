package cl.previred.desafio.component;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("util")
public class Util {
    public List<String> convertJsonArrayToList(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.get(i).toString());
        }
        return list;
    }

    public List<String> convertDateArrayToList(List<Date> array) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> result = new ArrayList<>();
        for(Date date : array) {
            String x = formatter.format(date);
            result.add(x);
        }
        return result;
    }

    public List<Date> getAllPeriodsInRange(Date startDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(endDate);
        List<Date> dateArray = new ArrayList<Date>();
        while (!c1.after(c2)) {
            dateArray.add(c1.getTime());
            c1.add(Calendar.MONTH, 1);
        }
        return dateArray;
    }

    public List<Date> getAllPeriodsInDateList(JSONArray jsonArray) throws JSONException {
        List<Date> dateArray = new ArrayList<Date>();
        for (int i = 0; i < jsonArray.length(); i++) {
            dateArray.add(convertStringToDate(jsonArray.get(i).toString()));
        }
        return dateArray;
    }

    public Date convertStringToDate(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            return  date;
        }

        return date;
    }
}
