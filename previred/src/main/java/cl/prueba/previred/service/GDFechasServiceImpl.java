package cl.prueba.previred.service;
import cl.prueba.previred.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Logger;
import java.util.logging.Level;


import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GDFechasServiceImpl implements GDFechasService {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    private final static Logger LOGGER = Logger.getLogger("cl.prueba.previred.service");

    @Override
    public GDFechas getCoins() throws ParseException {
        ResponseEntity<GDFechas> coins = restTemplate.getForEntity("http://127.0.0.1:8080/periodos/api", GDFechas.class);
        Date inicio = null;
        Date fin = null;
        try {
            inicio = new SimpleDateFormat("yyyy-MM-dd").parse(coins.getBody().getFechaCreacion());
            fin = new SimpleDateFormat("yyyy-MM-dd").parse(coins.getBody().getFechaFin());
        } catch (ParseException e) {
            LOGGER.log(Level.INFO, "problemas al setear variables" + e);
            e.printStackTrace();
        }
        List<String> fechasYaProcesadas = coins.getBody().getFechas().stream()
                .distinct()
                .collect(Collectors.toList());
        List<String> fFaltantes = new ArrayList<>();
        List<String> fFechas = coins.getBody().getFechas();
        fFaltantes = this.fechasFaltantes(inicio, fin, fFechas);
        List<String> fechasRepetidas = new ArrayList<>(fFaltantes);
        GDFechas fechaProcesada = new GDFechas();
        fechaProcesada.setId(coins.getBody().getId());
        fechaProcesada.setFechaCreacion(coins.getBody().getFechaCreacion());
        fechaProcesada.setFechaFin(coins.getBody().getFechaFin());
        fechaProcesada.setFechas(fechasYaProcesadas);
        fechaProcesada.setFechasFaltantes(fechasRepetidas);

        return fechaProcesada;
    }

    private List<String> fechasFaltantes(Date inicio, Date fin, List fFechas) {
        String c1 = trim1(inicio);
        String c2 = trim2(fin);
        List<String> fFaltantes = new ArrayList();
        LOGGER.log(Level.INFO, "entro al arreglo");
        while (!c1.equals(c2)) {
            try {
                Calendar c3 = Calendar.getInstance();
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(c1);
                c3.setTime(date1);
                c3.add(Calendar.MONTH, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                String date2 = format1.format(c3.getTime());
                c1 = date2;
                fFaltantes.add(c1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<String> resultado = new ArrayList<>();
        Collection<String>similar=new HashSet<String>();
        resultado.addAll(fFaltantes);
        resultado.addAll(fFechas);
        similar.addAll(fFechas);
        resultado.removeAll(similar);
        resultado = resultado.stream()
                .distinct()
                .collect(Collectors.toList());
        Collections.sort(resultado);
        return resultado;
    }

    private String trim1(Date inicio) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(inicio);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String c1Format = format1.format(c1.getTime());
        return c1Format;
    }

    private String trim2(Date fin) {
        Calendar c2 = Calendar.getInstance();
        c2.setTime(fin);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String c2Format = format1.format(c2.getTime());
        return c2Format;
    }
}
