package cl.previred.prueba.tecnica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author jgajardo
 *
 */

@Configuration
@ComponentScan (basePackages = "cl.previred.prueba.tecnica")
@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
public class AplicationPrevired {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AplicationPrevired.class);
		application.run(args);
	}
}
