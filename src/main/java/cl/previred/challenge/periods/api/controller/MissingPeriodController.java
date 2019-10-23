package cl.previred.challenge.periods.api.controller;

import cl.previred.challenge.periods.model.periods.MissingPeriodsResponse;
import cl.previred.challenge.periods.service.MissingPeriodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api to complete periods
 *
 * @author Luis Riveros - luis.riveros_ex@scotiabank.cl
 * @version 1.0.0 - 22-10-2019
 * @since 1.0.0 - 22-10-2019
 */
@RestController
@RequestMapping(value = "/missing")
public class MissingPeriodController {

    @Autowired
    private MissingPeriodsService service;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MissingPeriodsResponse> getMissingPeriods() {
        return new ResponseEntity<MissingPeriodsResponse>(service.completePeriods(), HttpStatus.OK);
    }
}
