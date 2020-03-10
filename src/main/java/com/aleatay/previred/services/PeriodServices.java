package com.aleatay.previred.services;

import com.aleatay.previred.exceptions.JsonException;
import com.aleatay.previred.model.MissingDateModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import static com.aleatay.previred.utils.JsonUtil.getObjectByJsonString;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 */
@Service
@PropertySource("classpath:apiPeriodos.properties")
public class PeriodServices extends MainRestClient {

    @Value("${api.periodos.url}")
    private String urlApiPeriod;

    public MissingDateModel getPeriods() throws JsonException {

        MissingDateModel periodResponseModel;

        String body = sendJsonRequest(urlApiPeriod);
        periodResponseModel = getObjectByJsonString(body, MissingDateModel.class);

       return periodResponseModel;
    }
}
