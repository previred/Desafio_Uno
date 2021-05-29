package com.desafio.periodosperdidos;

import com.desafio.periodosperdidos.config.Swagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Swagger.class})
public class PeriodosperdidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeriodosperdidosApplication.class, args);
    }

}
