package com.cox.igdd;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.cox.igdd.util.SwaggerConfiguration;


@SpringBootApplication
@Import(SwaggerConfiguration.class)
public class DemoApplication {
	
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(DemoApplication.class);

	
	public static void main(String[] args) {		 
		SpringApplication.run(DemoApplication.class, args);
		logger.info("*** Esperando peticiones por el puerto 4444");
		
	} 
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                 .addResourceLocations("classpath:/META-INF/resources/");
 }
	 

}
