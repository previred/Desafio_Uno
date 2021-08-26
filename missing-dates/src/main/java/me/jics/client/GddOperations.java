package me.jics.client;

import io.reactivex.rxjava3.core.Single;

public interface GddOperations {
    Single<Period> retrievePeriod();
}
