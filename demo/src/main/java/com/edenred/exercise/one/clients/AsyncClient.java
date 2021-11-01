package com.edenred.exercise.one.clients;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.Callable;

public abstract class AsyncClient {
    protected <T> Observable async(Callable<T> callable) {
        return Observable
                .empty()
                .observeOn(Schedulers.io())
                .fromCallable(callable)
                .observeOn(Schedulers.computation());
    }
}
