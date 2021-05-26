package cl.previred.presentation.api;


import cl.previred.infrastructure.adapters.exception.SourceSystemClientErrorException;
import cl.previred.infrastructure.adapters.exception.SourceSystemConstraintViolationsException;
import cl.previred.infrastructure.adapters.exception.SourceSystemServerErrorException;
import cl.previred.presentation.api.model.PeriodoResponse;
import cl.previred.presentation.api.model.error.ApiError;
import cl.previred.presentation.api.model.error.ApiErrorResponse;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.validation.ConstraintViolation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebClient
@WebMvcTest(controllers = DateManagementController.class)
class DateManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DateManagementFacade dateManagementFacade;

    @Test
    void shouldFiltered() throws Exception {

        PeriodoResponse periodoResponse = buildPeriodoResponse();

        given(dateManagementFacade.filtered())
                .willReturn(periodoResponse);

        ResultActions resultActions = mockMvc.perform(getCallToFiltered())
                        .andExpect(status().isOk());
 
        resultActions
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.fechaCreacion").exists())
                .andExpect(jsonPath("$.fechaCreacion").value("2020-01-08"))
                .andExpect(jsonPath("$.fechaFin").exists())
                .andExpect(jsonPath("$.fechaFin").value("2020-01-08"))
                .andExpect(jsonPath("$.fechas").exists())
                .andExpect(jsonPath("$.fechas").value("2020-01-08"))
                .andExpect(jsonPath("$.fechasFaltantes").exists())
                .andExpect(jsonPath("$.fechasFaltantes").value("2020-01-08"));
        
        List <LocalDate> ld = new ArrayList<LocalDate>();	
    	ld.add(LocalDate.parse("2020-01-08"));
        
        assertEquals(periodoResponse.getFechaCreacion(),LocalDate.parse("2020-01-08"));
        assertEquals(periodoResponse.getFechaFin(),LocalDate.parse("2020-01-08"));
        assertEquals(periodoResponse.getFechasRec(),ld);
        assertEquals(periodoResponse.getFechasFal(),ld);
        assertEquals(periodoResponse.getId(),Long.valueOf(1L));


        
    }

 
 

    @Test
    void shouldHandleSourceSystemClientErrorException() throws Exception {


        ApiError apiError = new ApiError(400, "some 400 title", "some 400 detail", "some pointer");
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(Collections.singletonList(apiError));

        given(dateManagementFacade.filtered())
                .willThrow(new SourceSystemClientErrorException(new RuntimeException("some message"),"SYSTEM", apiErrorResponse, HttpStatus.BAD_REQUEST));

        ResultActions resultActions = mockMvc.perform(getCallToFiltered()).andExpect(status().isBadRequest());

        resultActions
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.length()").value(1));

        resultActions
                .andExpect(jsonPath("$.errors[0].status").value("400"))
                .andExpect(jsonPath("$.errors[0].title").value("some 400 title"))
                .andExpect(jsonPath("$.errors[0].detail").value("some 400 detail"))
                .andExpect(jsonPath("$.errors[0].source").exists())
                .andExpect(jsonPath("$.errors[0].source.pointer").value("some pointer"));
    }

    @Test
    void shouldHandleSourceSystemServerErrorException() throws Exception {


        ApiError apiError = new ApiError(500, "some 500 title", "some 500 detail", "some pointer");
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(Collections.singletonList(apiError));

        given(dateManagementFacade.filtered())
                .willThrow(new SourceSystemServerErrorException(new RuntimeException("some cause"),
                        "SYSTEM", apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR));

        ResultActions resultActions =
                mockMvc.perform(getCallToFiltered())
                        .andExpect(status().isInternalServerError());

        resultActions
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.length()").value(1));

        resultActions
                .andExpect(jsonPath("$.errors[0].status").value("500"))
                .andExpect(jsonPath("$.errors[0].title").value("some 500 title"))
                .andExpect(jsonPath("$.errors[0].detail").value("some 500 detail"))
                .andExpect(jsonPath("$.errors[0].source").exists())
                .andExpect(jsonPath("$.errors[0].source.pointer").value("some pointer"));
    }


    @Test
    void shouldHandleSourceSystemConstraintViolationsException() throws Exception {

        ConstraintViolation<Object> constraintViolation =
                ConstraintViolationImpl.forBeanValidation("", null,
                        null, "must not be empty", Object.class, "", null,
                        null, PathImpl.createPathFromString("response.id"), null, null);
        Set<ConstraintViolation<Object>> violations = Collections.singleton(constraintViolation);

        given(dateManagementFacade.filtered())
                .willThrow(new SourceSystemConstraintViolationsException("SYSTEM", violations));

        ResultActions resultActions =
                mockMvc.perform(getCallToFiltered())
                        .andExpect(status().isUnprocessableEntity());

        resultActions
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.length()").value(1));

        resultActions
        .andExpect(jsonPath("$.errors[0].status").value("422"))
        .andExpect(jsonPath("$.errors[0].title").value("Respuesta del servicio SYSTEM es inválida"))
        .andExpect(jsonPath("$.errors[0].detail").value("response.id must not be empty"))
        .andExpect(jsonPath("$.errors[0].source").exists())
        .andExpect(jsonPath("$.errors[0].source.pointer").value("response.id"));
    }

    @Test
    void shouldHandleException() throws Exception {


        given(dateManagementFacade.filtered()).willThrow(new RuntimeException("some specific message"));

        ResultActions resultActions = mockMvc.perform(getCallToFiltered()).andExpect(status().isInternalServerError());

        resultActions
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors.length()").value(1));

        resultActions
                .andExpect(jsonPath("$.errors[0].status").value("500"))
                .andExpect(jsonPath("$.errors[0].title").value("Error interno"))
                .andExpect(jsonPath("$.errors[0].detail").value("some specific message"))
                .andExpect(jsonPath("$.errors[0].source").exists());
    }

    private MockHttpServletRequestBuilder getCallToFiltered() {
        return get("/v1/date_management")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
    }

    private PeriodoResponse buildPeriodoResponse() {

    	List <LocalDate> ld = new ArrayList<LocalDate>();	
    	ld.add(LocalDate.parse("2020-01-08"));
    	
        return 
        PeriodoResponse.builder()
        .id(1L)
        .fechaCreacion(LocalDate.parse("2020-01-08") )
        .fechaFin(LocalDate.parse("2020-01-08"))
        .fechasRec(ld)
        .fechasFal(ld)
        .build();
        
    }



}