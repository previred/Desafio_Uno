package me.jics.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Recoverable;
import io.micronaut.retry.annotation.Retryable;
import io.reactivex.rxjava3.core.Single;


/**
 * Http client. This consumes from an external http services.
 * It consumes json and translate it into a java objects.
 * Also had a fallback and it retries 3 times.
 * @author Juan Cuzmar
 * @version 1.0
 */
@Client("${services.gdd.url}")
@Retryable
@Recoverable(api = GddOperations.class)
public interface GddClient extends GddOperations {
    /**
     * Here we consume the services given an url path.
     * @return single result from the service
     */
    @Get("${services.gdd.path}")
    Single<Period> retrievePeriod();
}
