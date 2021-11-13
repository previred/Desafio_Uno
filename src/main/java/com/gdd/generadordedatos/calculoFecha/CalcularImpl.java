package com.gdd.generadordedatos.calculoFecha;

import com.gdd.generadordedatos.dto.FechaCalculadas;
import com.gdd.generadordedatos.dto.FechaEntradas;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalcularImpl implements CalcularService {

    private final static Integer repeticion = 100;
    private final static Integer uno = 1;

    @Override
    public FechaCalculadas getFecha(FechaEntradas fechaEntradas) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date dia1 = formato.parse(fechaEntradas.getFechaCreacion());
        Date dia2 = formato.parse(fechaEntradas.getFechaFin());
        List<String> nombre = new ArrayList<>();
        for (int i=0;i<getDaysBetweenDates(dia1,dia2).size();i++) {
            String nuevoDia = formato.format(getDaysBetweenDates(dia1,dia2).get(i));
            nombre.remove(fechaEntradas.getFechaCreacion());
            nombre.remove(fechaEntradas.getFechaFin());
            nombre.add(nuevoDia);

        }
        List<String> nombreSinDuplicados = nombre
                .stream()
                .distinct()
                .collect(Collectors.toList());

        Collection<String> listOne = getRandomElement(nombreSinDuplicados,repeticion);
        Collection<String> listTwo = fechaEntradas.getFechas();
        Collection<String> similar = new HashSet<String>( listOne );
        Collection<String> different = new HashSet<String>();
        different.addAll( listOne );
        different.addAll( listTwo );
        similar.retainAll( listTwo.stream().distinct().collect(Collectors.toList()) );
        different.removeAll( similar.stream().distinct().collect(Collectors.toList()) );
        FechaCalculadas fechaCalculadas =new FechaCalculadas();
        fechaCalculadas.setFechaCreacion(fechaEntradas.getFechaCreacion());
        fechaCalculadas.setFechaFin(fechaEntradas.getFechaFin());
        fechaCalculadas.setFechasRecibidas(fechaEntradas.getFechas());
        fechaCalculadas.setFechasFaltantes(different.stream().collect(Collectors.toList()));
        return fechaCalculadas;
    }

    public static List getDaysBetweenDates(Date startdate, Date enddate) {
        List dates = new ArrayList();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.MONTH, uno); }
        return dates;
    }

    public static List<String> getRandomElement(List<String> list,int totalItems) {
        Random rand = new Random();
        List<String> tempList = new ArrayList<>();
        for (int i=0;i< totalItems; i++) {
            int randomIndex = rand.nextInt(list.size());
            tempList.add(list.get(randomIndex));
        }
        return tempList;
    }
}
