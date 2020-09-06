package com.previred.desafio.controller;
import com.previred.desafio.dto.GddDto;
import com.previred.desafio.service.GddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FechaController {

    @Autowired private GddService gddService;

    @GetMapping("/genDates")
    @ResponseBody
    public GddDto genDates() {

        return gddService.genDates();

    }

}
