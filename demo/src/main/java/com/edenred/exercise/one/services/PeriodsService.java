package com.edenred.exercise.one.services;

import com.edenred.exercise.one.models.PeriodsInfoModel;
import rx.Observable;

public interface PeriodsService {

    public Observable<PeriodsInfoModel> getPeriodsInfo();
}
