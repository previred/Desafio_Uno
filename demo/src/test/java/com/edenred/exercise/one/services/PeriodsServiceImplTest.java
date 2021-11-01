package com.edenred.exercise.one.services;

import com.edenred.exercise.one.clients.GDDClient;
import com.edenred.exercise.one.dtos.GddResponseDto;
import com.edenred.exercise.one.models.PeriodsInfoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rx.Observable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class PeriodsServiceImplTest {

    private PeriodsService periodsService;
    private GDDClient client;

    @BeforeEach
    public void setUp() {
        client = mock(GDDClient.class);
        periodsService = new PeriodsServiceImpl(client);
    }

    @Test
    public void getPeriodsInfo_whenCalculateMissingDates_thenReturnPeriodsInfoModel() {
        // Arrange
        GddResponseDto gddResponseDto = new GddResponseDto();
        gddResponseDto.dateCreate = "1969-03-01";
        gddResponseDto.dateEnd = "1970-01-01";
        gddResponseDto.dates = Arrays.asList("1969-03-01",
                "1969-05-01",
                "1969-09-01",
                "1970-01-01");

        doReturn(Observable.just(gddResponseDto)).when(client).callServiceGdd();

        // Act
        PeriodsInfoModel response = periodsService.getPeriodsInfo().toBlocking().first();

        // Assert
        assertNotNull(response);
        assertEquals(7, response.missingDates.size());
    }

    @Test
    public void getPeriodsInfo_whenDateCreateNull_thenReturnPeriodsInfoModel() {
        // Arrange
        GddResponseDto gddResponseDto = new GddResponseDto();
        gddResponseDto.dateEnd = "1970-01-01";
        gddResponseDto.dates = Arrays.asList("1969-03-01",
                "1969-05-01",
                "1969-09-01",
                "1970-01-01");

        doReturn(Observable.just(gddResponseDto)).when(client).callServiceGdd();

        // Act
        PeriodsInfoModel response = periodsService.getPeriodsInfo().toBlocking().first();

        // Assert
        assertNotNull(response);
        assertNull(response.missingDates);
    }

    @Test
    public void getPeriodsInfo_whenDateEndNull_thenReturnPeriodsInfoModel() {
        // Arrange
        GddResponseDto gddResponseDto = new GddResponseDto();
        gddResponseDto.dateEnd = "1970-01-01";
        gddResponseDto.dates = Arrays.asList("1969-03-01",
                "1969-05-01",
                "1969-09-01",
                "1970-01-01");

        doReturn(Observable.just(gddResponseDto)).when(client).callServiceGdd();

        // Act
        PeriodsInfoModel response = periodsService.getPeriodsInfo().toBlocking().first();

        // Assert
        assertNotNull(response);
        assertNull(response.missingDates);
    }

}