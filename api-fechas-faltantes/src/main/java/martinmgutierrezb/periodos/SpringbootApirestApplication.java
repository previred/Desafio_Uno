package martinmgutierrezb.periodos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApirestApplication.class, args);
	}

}
