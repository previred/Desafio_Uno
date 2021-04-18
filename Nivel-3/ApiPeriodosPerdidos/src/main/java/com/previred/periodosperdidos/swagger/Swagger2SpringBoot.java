package com.previred.periodosperdidos.swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients(basePackages = {"com.previred.periodosperdidos.client"})
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.previred.periodosperdidos.swagger", 
		"com.previred.periodosperdidos.swagger.codegen.api" , 
		"com.previred.periodosperdidos.swagger.codegen.config",
		"com.previred.periodosperdidos.servicio"
		})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
