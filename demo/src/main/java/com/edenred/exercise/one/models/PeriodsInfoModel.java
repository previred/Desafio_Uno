package com.edenred.exercise.one.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodsInfoModel {

    public long id;
    public String dateCreate;
    public String dateEnd;
    public List<String> dates;
    public List<String> missingDates;
}
