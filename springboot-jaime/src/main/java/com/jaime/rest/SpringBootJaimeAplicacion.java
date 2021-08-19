package com.jaime.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication 
public class SpringBootJaimeAplicacion {

    public static void main(String[] args) {
        //SpringApplication.run(SpringBootDemoApplication.class, args);

        SpringApplication app = new SpringApplication(SpringBootJaimeAplicacion.class);
        
        /* 
         * Configuracion de puerto vía programación, via properties es en application.properties 
         */
        // app.setDefaultProperties(Collections.singletonMap("server.port", "8000"));
        app.run(args);        

    }

}
