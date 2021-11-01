package com.edenred.exercise.one.clients;

import com.edenred.exercise.one.dtos.GddResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

@Component
public class GDDClient extends AsyncClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.service.gdd}")
    private String urlServiceGdd;

    public Observable<GddResponseDto> callServiceGdd(){
        return async(() -> {
            GddResponseDto response = restTemplate.getForObject(urlServiceGdd, GddResponseDto.class);

            if(response == null) {
                return null;
            }

            return response;
        });
    }
}
