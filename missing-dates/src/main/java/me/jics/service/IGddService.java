package me.jics.service;

import io.reactivex.rxjava3.core.Single;

public interface IGddService {
    Single<MissingPeriod> getMissingPeriods();
}
