package cl.previred.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.rest.response.CompletePeriodResponse;
import cl.previred.services.GDDCompleteService;

@RestController
public class PeriodController {

	private static final Logger logger = LogManager.getLogger(PeriodController.class);

	@Autowired
	GDDCompleteService gddCompleteService;

	@RequestMapping(value = "/missingPeriods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CompletePeriodResponse> getMissingPeriods() {
		logger.info("[getMissingPeriods] Start");
		CompletePeriodResponse completePeriodResponse = new CompletePeriodResponse();
		try {
			completePeriodResponse = gddCompleteService.getCompletePeriod();

			logger.info("[getMissingPeriods] Output: " + completePeriodResponse.toString());
			return new ResponseEntity<CompletePeriodResponse>(completePeriodResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("[getMissingPeriods] Error: ", e);
			return new ResponseEntity<CompletePeriodResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}