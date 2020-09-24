package com.previred.desafio.util;

import com.previred.desafio.model.DataGeneratorResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * static transversal functions
 */
public class PreviredUtils {

    /**
     * get a range of dates
     *
     * @param dataGeneratorResponse the model to transport the dates
     * @param startYearLimit        the oldest possible year
     * @param endYearLimit          the newest year
     * @return a model with both dates
     */
    public static DataGeneratorResponse getLimitDates(DataGeneratorResponse dataGeneratorResponse, LocalDate startYearLimit, LocalDate endYearLimit) {
        do {
            dataGeneratorResponse.setStartDate(randomDate(startYearLimit, endYearLimit));
            dataGeneratorResponse.setEndDate(randomDate(startYearLimit, endYearLimit));
        } while (monthsDistance(dataGeneratorResponse) < 100);
        return dataGeneratorResponse;
    }

    /**
     * get random dates between the limits of the dataGeneratorResponse
     *
     * @param dataGeneratorResponse the model
     */
    public static void getRandomDates(DataGeneratorResponse dataGeneratorResponse) {
        dataGeneratorResponse.setDateList(new ArrayList<>());
        Random random = new Random();
        int randomIterationSize = random.nextInt((100 - 70) + 1) + 70;
        while (dataGeneratorResponse.getDateList().size() < randomIterationSize) {
            LocalDate randomDate = randomDate(dataGeneratorResponse.getStartDate(), dataGeneratorResponse.getEndDate());
            if (randomDate.isBefore(dataGeneratorResponse.getEndDate()) && randomDate.isAfter(dataGeneratorResponse.getStartDate())) {
                if (!dataGeneratorResponse.getDateList().contains(randomDate)) {
                    dataGeneratorResponse.getDateList().add(randomDate);
                }
            }
        }
    }

    /**
     * get random date between some dates
     *
     * @param startDate the lower limit
     * @param endDate   the higher limit
     * @return a date
     */
    private static LocalDate randomDate(LocalDate startDate, LocalDate endDate) {
        Random random = new Random();
        Integer startYear = startDate.getYear();
        Integer endYear = endDate.getYear();
        Integer randomYear = random.nextInt(endYear - startYear + 1) + startYear;
        Integer randomMonth = random.nextInt(12 - 1 + 1) + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(randomYear, randomMonth, 1, 0, 0, 0);
        return cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    /**
     * get the distance in months between 2 dates
     *
     * @param dataGeneratorResponse the model to transport the dates
     * @return a integer that reprensent the difference
     */
    private static Integer monthsDistance(DataGeneratorResponse dataGeneratorResponse) {
        int diffYear = dataGeneratorResponse.getEndDate().getYear() - dataGeneratorResponse.getStartDate().getYear();
        return diffYear * 12 + dataGeneratorResponse.getEndDate().getMonthValue() - dataGeneratorResponse.getStartDate().getMonthValue();
    }

    /**
     * fill the missing dates for the dataGeneratorResponse
     *
     * @param dataGeneratorResponse the model to transport the data
     */
    public static void fillMissingDates(DataGeneratorResponse dataGeneratorResponse) {
        dataGeneratorResponse.setMissingDatesList(new ArrayList<>());
        LocalDate tempDate = dataGeneratorResponse.getStartDate();
        while (tempDate.isBefore(dataGeneratorResponse.getEndDate())) {
            if (!dataGeneratorResponse.getDateList().contains(tempDate)) {
                dataGeneratorResponse.getMissingDatesList().add(tempDate);
            }
            tempDate = tempDate.plusMonths(1);
        }
    }


}
