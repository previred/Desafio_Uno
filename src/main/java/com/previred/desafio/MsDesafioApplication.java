package com.previred.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MsDesafioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsDesafioApplication.class, args);
    }

}
