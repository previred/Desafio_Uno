package com.edenred.exercise.one.controllers;

import com.edenred.exercise.one.models.PeriodsInfoModel;
import com.edenred.exercise.one.services.PeriodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

/**
 * Controlador que expone los diferentes metodos rest que se necesitan para el
 * desafio uno nivel 3
 *
 * @author kenavarro
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.OPTIONS})
public class PeriodsController {

    @Autowired
    private PeriodsService periodsService;

    @GetMapping("/periods")
    public Observable<PeriodsInfoModel> getMissingDates() {
        return periodsService.getPeriodsInfo()
                .map(periodsInfoModel -> periodsInfoModel);
    }
}
