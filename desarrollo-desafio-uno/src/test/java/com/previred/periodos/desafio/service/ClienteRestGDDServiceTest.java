package com.previred.periodos.desafio.service;

import com.previred.periodos.desafio.model.Periodo;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpResponse.response;

/**
 * Created by sati on 23-03-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ClienteRestGDDServiceTest {

    ClientAndServer mockServer;

    @Value("classpath:responseGdd/periodos.json")
    Resource rs;

    @Autowired
    ClienteRestGDDService clienteRestGDDService;

    @BeforeEach
    public void startServer() {
        mockServer = startClientAndServer(1080);
    }

    @AfterEach
    public void stopServer() {
        mockServer.stop();
    }

    @Test
    void getPeriodos() throws IOException {
        this.configurateMockServer(rs);
        Periodo periodos = clienteRestGDDService.getPeriodos();
        assertEquals(LocalDate.parse("1989-06-01"),periodos.getFechaCreacion());
        assertEquals(LocalDate.parse("2016-06-01"),periodos.getFechaFin());
        assertEquals(101,periodos.getFechas().size());
        assertNull(periodos.getFechasFaltantes());
    }

    private void configurateMockServer(Resource rs) throws IOException {
        mockServer.when(HttpRequest.request().withMethod("GET")).respond(
                response().withStatusCode(200).withBody(new String(IOUtils.toByteArray(rs.getInputStream())))
                        .withHeaders(new Header("Content-Type", "application/json; charset=utf-8"))
        );
    }

}