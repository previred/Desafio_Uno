package cl.hermann.periodos.obtener.faltantes;

import cl.hermann.periodos.modelo.Periodo;
import cl.hermann.periodos.utils.PeriodoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 *
 * @author Hermann Arriagada Mendez
 */
@SpringBootApplication
public class ObtenerPeriodosFaltantes implements CommandLineRunner {

    //Se establece la aplicacion con SpringBoot
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(ObtenerPeriodosFaltantes.class)
                .logStartupInfo(false)
                .web(false);
        app.application().setBannerMode(Banner.Mode.OFF);
        app.application().setLogStartupInfo(false);
        app.application().setWebEnvironment(false);
        app.application().run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0 != null && arg0.length > 1) {

            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();

            try (FileReader reader = new FileReader(arg0[0])) {
                //Read JSON file
                Object obj = jsonParser.parse(reader);

                org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) obj;

                LocalDate fechaCreacion = LocalDate.parse((String) jsonObject.get("fechaCreacion"));
                LocalDate fechaFin = LocalDate.parse((String) jsonObject.get("fechaFin"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("fechas");

                List<LocalDate> fechas = new ArrayList<>();
                List<LocalDate> fechasFaltantes = new ArrayList<>();
                for (int i = 0; i < jsonArray.size(); i++) {
                    fechas.add(LocalDate.parse((String) jsonArray.get(i)));
                }
                
                if(!fechas.contains(fechaCreacion)){
                    fechasFaltantes.add(fechaCreacion);
                }

                //Ordenar las fechas
                Collections.sort(fechas, (x, y) -> x.compareTo(y));

                for (int i = 0; i < fechas.size() - 1; i++) {
                    List<LocalDate> fechasFaltantesTmp;
                    if (i == 0) {
                        fechasFaltantesTmp = PeriodoUtils.obtenerEntreFechas(fechaCreacion, fechas.get(i));
                        fechasFaltantesTmp.addAll(PeriodoUtils.obtenerEntreFechas(fechas.get(i), fechas.get(i + 1)));
                    } else if (i > 0 && i < fechas.size() - 1) {
                        fechasFaltantesTmp = PeriodoUtils.obtenerEntreFechas(fechas.get(i), fechas.get(i + 1));
                    } else {
                        fechasFaltantesTmp = PeriodoUtils.obtenerEntreFechas(fechas.get(i), fechaFin);
                    }
                    fechasFaltantesTmp.forEach((fechaFaltante) -> {
                        fechasFaltantes.add(fechaFaltante);
                    });
                }
                
                if(!fechas.contains(fechaFin)){
                    fechasFaltantes.add(fechaFin);
                }

                Periodo periodo = new Periodo(
                        (Long) jsonObject.get("id"),
                        fechaCreacion,
                        fechaFin,
                        null,
                        fechasFaltantes
                );

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String jsonString = mapper.writeValueAsString(periodo);

                List<LocalDate> todasLasFechas = new ArrayList<>(fechas);
                todasLasFechas.addAll(periodo.getFechasFaltantes());

                Collections.sort(todasLasFechas, (x, y) -> x.compareTo(y));

                try (FileWriter file = new FileWriter(arg0[1])) {
                    file.write(jsonString);
                    System.out.println("Archivo '" + arg0[1] + "' creado correctamente");
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else if (arg0.length == 1) {
            System.out.println("ADVERTENCIA: Falta un argumento (nombre archivo salida JSON)");
        } else {
            System.out.println("ADVERTENCIA: Falta dos argumentos (nombre de archivo de entrada JSON y nombre archivo salida JSON)");
        }
    }
}
