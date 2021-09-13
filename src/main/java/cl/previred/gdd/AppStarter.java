package cl.previred.gdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AppStarter {

  public static void main(String[] args) {
    SpringApplication.run(AppStarter.class, args);
  }
}
