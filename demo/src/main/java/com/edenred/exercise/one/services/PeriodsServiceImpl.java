package com.edenred.exercise.one.services;

import com.edenred.exercise.one.clients.GDDClient;
import com.edenred.exercise.one.dtos.GddResponseDto;
import com.edenred.exercise.one.exceptions.ApiApplicationException;
import com.edenred.exercise.one.models.PeriodsInfoModel;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PeriodsServiceImpl implements PeriodsService {

    private final GDDClient client;

    @Autowired
    public PeriodsServiceImpl(GDDClient client) {
        this.client = client;
    }


    @Override
    public Observable<PeriodsInfoModel> getPeriodsInfo() {
        return client.callServiceGdd()
                .onErrorResumeNext(error -> {
                    String errorMsg = String.format("There was an error parsing msg service GDD", error);
                    log.error(errorMsg);

                    return Observable.error(new ApiApplicationException(errorMsg, error));
                })
                .flatMap(this::mapperResponse);
    }

    private Observable<PeriodsInfoModel> mapperResponse(GddResponseDto gddResponseDto) {
        PeriodsInfoModel model = PeriodsInfoModel.builder()
                .id(gddResponseDto.id)
                .dateCreate(gddResponseDto.dateCreate)
                .dateEnd(gddResponseDto.dateEnd)
                .dates(gddResponseDto.dates)
                .missingDates(calculateMissingDates(gddResponseDto.dateCreate, gddResponseDto.dateEnd, gddResponseDto.dates))
                .build();

        return Observable.just(model);
    }

    private List<String> calculateMissingDates(String dateCreate, String dateEnd, List<String> dates) {
        if(Strings.isNullOrEmpty(dateCreate)){
            return null;
        }
        if(Strings.isNullOrEmpty(dateEnd)){
            return null;
        }

        long months = ChronoUnit.MONTHS.between(LocalDate.parse(dateCreate).withDayOfMonth(1), LocalDate.parse(dateEnd).withDayOfMonth(1));

        List<String> missingDates = new ArrayList<>();
        LocalDate verifiedDay = LocalDate.parse(dateCreate);

        for(int i=0; i <= months; i++){
            if(!dates.contains(verifiedDay.toString())){
                missingDates.add(verifiedDay.toString());
            }
            verifiedDay = verifiedDay.plus(1,ChronoUnit.MONTHS );
        }

        return missingDates;
    }
}
