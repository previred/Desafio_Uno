package com.previred.desafio.controller;

import com.previred.desafio.model.DataGeneratorResponse;
import com.previred.desafio.service.CorrelativeIdService;
import com.previred.desafio.util.PreviredUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * controller for data
 */
@RestController
@RequestMapping("/")
public class DataGeneratorController extends CommonController {

    /**
     * the correlative id service
     */
    @Autowired
    private CorrelativeIdService correlativeIdService;

    /**
     * the data generator
     *
     * @return a DataGeneratorResponse
     */
    @RequestMapping(path = "gdd", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public DataGeneratorResponse generator() {
        DataGeneratorResponse dataGeneratorResponse = new DataGeneratorResponse();
        dataGeneratorResponse.setId(correlativeIdService.getId());
        PreviredUtils.getLimitDates(dataGeneratorResponse, getStartDate(), getEndDate());
        PreviredUtils.getRandomDates(dataGeneratorResponse);
        dataGeneratorResponse.orderList();
        return dataGeneratorResponse;
    }

    /**
     * fill with the missing dates of the model dataGeneratorResponse (level 1)
     *
     * @param dataGeneratorResponse the model to transport the data
     * @return a DataGeneratorResponse
     */
    @RequestMapping(path = "level1", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public DataGeneratorResponse level1(@RequestBody DataGeneratorResponse dataGeneratorResponse) {
        PreviredUtils.fillMissingDates(dataGeneratorResponse);
        dataGeneratorResponse.orderList();
        return dataGeneratorResponse;
    }

    /**
     * fill with the missing dates of the model dataGeneratorResponse (level 2)
     * and export the data on txt file
     *
     * @param response the httpResponse
     * @throws IOException if there is any problem with exporting the file
     */
    @RequestMapping(path = "level2", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void level2(HttpServletResponse response) throws IOException {
        DataGeneratorResponse dataGeneratorResponse = generator();
        PreviredUtils.fillMissingDates(dataGeneratorResponse);
        dataGeneratorResponse.orderList();
        response.setContentType("text/plain; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=myFile.txt");
        ServletOutputStream out = response.getOutputStream();
        out.println("fecha creacion: " + dataGeneratorResponse.getStartDate());
        out.println("fecha fin: " + dataGeneratorResponse.getEndDate());
        if (!dataGeneratorResponse.getDateList().isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("fechas recibidas: ");
            dataGeneratorResponse.getDateList().forEach(localDate -> {
                stringBuilder.append(localDate + ", ");
            });
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            out.println(stringBuilder.toString());
        }
        if (!dataGeneratorResponse.getMissingDatesList().isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("fechas faltantes: ");
            dataGeneratorResponse.getMissingDatesList().forEach(localDate -> {
                stringBuilder.append(localDate + ", ");
            });
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            out.println(stringBuilder.toString());
        }
        out.flush();
        out.close();
    }

    /**
     * fill with the missing dates of the model dataGeneratorResponse (level 3)
     *
     * @return a DataGeneratorResponse
     */
    @RequestMapping(path = "level3", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public DataGeneratorResponse level3() {
        DataGeneratorResponse dataGeneratorResponse = generator();
        PreviredUtils.fillMissingDates(dataGeneratorResponse);
        dataGeneratorResponse.orderList();
        return dataGeneratorResponse;
    }
}
