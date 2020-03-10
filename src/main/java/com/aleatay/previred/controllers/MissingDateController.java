package com.aleatay.previred.controllers;

import com.aleatay.previred.exceptions.JsonException;
import com.aleatay.previred.model.MissingDateModel;
import com.aleatay.previred.services.MissingDateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ConnectException;

/**
 * @author Alexis Rivas
 * @date 03-09-2020
 */
@RestController
public class MissingDateController {

    private MissingDateServices missingDateServices;

    @Autowired
    public MissingDateController(MissingDateServices missingDateServices){
        this.missingDateServices = missingDateServices;
    }

    @GetMapping("/getMissingDates")
    public ResponseEntity<MissingDateModel> getMissingDates(){

        MissingDateModel periodResponseModel;

        try {
            periodResponseModel = missingDateServices.getMissingDate();
            return new ResponseEntity<>(periodResponseModel, HttpStatus.OK);
        } catch (JsonException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
