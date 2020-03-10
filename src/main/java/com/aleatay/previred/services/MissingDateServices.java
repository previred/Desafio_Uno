package com.aleatay.previred.services;

import com.aleatay.previred.exceptions.JsonException;
import com.aleatay.previred.model.MissingDateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 */
@Service
public class MissingDateServices {

    private PeriodServices periodServices;

    @Autowired
    public MissingDateServices(PeriodServices periodServices){
        this.periodServices = periodServices;
    }

    public MissingDateModel getMissingDate() throws JsonException {

        MissingDateModel missingDateModel = periodServices.getPeriods();

        String startDate = missingDateModel.getStartDate();
        String endDate = missingDateModel.getEndDate();

        long monthsBetween = ChronoUnit.MONTHS.between(
                LocalDate.parse(startDate).withDayOfMonth(1),
                LocalDate.parse(endDate).withDayOfMonth(1));

        int startDay = LocalDate.parse(startDate).getDayOfMonth();
        int startMonth = LocalDate.parse(startDate).getMonthValue();
        int startYear = LocalDate.parse(startDate).getYear();

        List<String> dates = new ArrayList<>();

        for (int i = 0; i < monthsBetween; i++, startMonth++){
            if(startMonth > 12){
                startMonth = 1;
                startYear++;
            }

            dates.add(startYear + "-" + completeDate(String.valueOf(startMonth)) + "-" + completeDate(String.valueOf(startDay)));

        }

        for(String date : missingDateModel.getInputsDates()) {
            dates.remove(date);
        }

        missingDateModel.setMissingDates(dates);

        return missingDateModel;
    }

    private static String completeDate(String date) {

        StringBuilder stringBuilderDate = new StringBuilder();
        stringBuilderDate.append(date.length() == 1 ? "0" : "").append(date);
        return stringBuilderDate.toString();
    }
}
