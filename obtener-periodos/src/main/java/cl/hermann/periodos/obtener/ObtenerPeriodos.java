package cl.hermann.periodos.obtener;

import cl.hermann.periodos.modelo.Periodo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Hermann Arriagada Mendez
 */
@SpringBootApplication
public class ObtenerPeriodos implements CommandLineRunner {

    public static String URL_API = "http://127.0.0.1:8080/periodos/api";

    //Se establece la aplicacion con Springboot
    public static void main(String[] args) {
        SpringApplicationBuilder app = new SpringApplicationBuilder(ObtenerPeriodos.class)
                .logStartupInfo(false);
        app.application().setBannerMode(Banner.Mode.OFF);
        app.application().setLogStartupInfo(false);
        app.application().setWebEnvironment(false);
        app.application().run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        try {
            if (arg0 != null && arg0.length > 0) {
                RestTemplate plantilla = new RestTemplate();
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

                //Consumo del API Rest
                ResponseEntity<String> result = plantilla.exchange(URL_API, HttpMethod.GET, entity, String.class);

                //Generar Objeto JSON en base a la respuesta
                JSONObject jsonObject = new JSONObject(result.getBody());
                LocalDate fechaCreacion = LocalDate.parse(jsonObject.getString("fechaCreacion"));
                LocalDate fechaFin = LocalDate.parse(jsonObject.getString("fechaFin"));
                JSONArray jsonArray = jsonObject.getJSONArray("fechas");

                List<LocalDate> fechas = new ArrayList<>();
                
                for (int i = 0; i < jsonArray.length(); i++) {
                    fechas.add(LocalDate.parse(jsonArray.getString(i)));
                }

                //Ordenar las fechas
                Collections.sort(fechas, (x, y) -> x.compareTo(y));

                Periodo periodo = new Periodo(
                        jsonObject.getLong("id"),
                        fechaCreacion,
                        fechaFin,
                        fechas,
                        null
                );

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                String jsonString = mapper.writeValueAsString(periodo);

                //Genera un archivo de entrada JSON con las fechas obtenidas
                try (FileWriter file = new FileWriter(arg0[0])) {
                    file.write(jsonString);
                    System.out.println("Archivo '" + arg0[0] + "' creado correctamente");
                }

            } else {
                System.out.println("ADVERTENCIA: Falta un argumento (nombre de archivo de salida JSON)");
            }

        } catch (JSONException | RestClientException e) {
            System.out.println(e.getMessage());
        }
    }
}
