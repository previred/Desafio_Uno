package me.jics.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.netty.handler.codec.http.HttpContent;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import me.jics.service.GddService;
import me.jics.service.MissingPeriod;

/**
 * Restful API that complete missing date given a range.
 *
 * @author Juan Cuzmar
 * @version 1.0
 */
@Slf4j
@Controller("/missing-dates")
public class MissingDatesController {

    private final GddService gddService;

    /**
     * Constructor of the MissingDatesController class
     * Here we are injecting some dependencies
     *
     * @param gddService the services layer
     */
    public MissingDatesController(GddService gddService) {
        this.gddService = gddService;
    }

    /**
     * This function will be in charge of delivering the previously processed data.
     *
     * @return single result of the missing dates
     */
    @Get()
    @ApiResponse(
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(type = "MissingPeriod", description = "Expected result proposed by the challenge"))
    )
    public Single<MissingPeriod> getMissingDates() {
        log.info("Entering to the controller method: getMissingDates");
        return this.gddService.getMissingPeriods();
    }
}