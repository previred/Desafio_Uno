package com.example.desafio_uno.client;

import com.example.desafio_uno.entity.Previred;
import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class PreviredClient implements PreviredClientService{

    private final RestTemplate restTemplate;

    @Value("${urlApiPrevired}")
    String urlData;

    @Override
    public Previred obtenerFechasPrevired() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = this.restTemplate.exchange(
                urlData,
                HttpMethod.GET,
                request,
                String.class);

        Previred data = gson.fromJson(response.getBody(), Previred.class);
        return data;
    }

    private static final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
        }
    }).create();
}
