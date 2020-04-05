/**
 * 
 */
package com.previred.fechas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.previred.fechas.config.Properties;



/**
 * @author : Jorge Bravo Aliaga
 *
 */
@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class Application extends SpringBootServletInitializer {

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

}
