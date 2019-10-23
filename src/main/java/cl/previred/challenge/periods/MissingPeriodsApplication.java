package cl.previred.challenge.periods;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@SpringBootApplication(scanBasePackages = "cl.previred.challenge.periods")
public class MissingPeriodsApplication {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {
        SpringApplication.run(MissingPeriodsApplication.class, args);
    }

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        builder.simpleDateFormat(DEFAULT_DATE_FORMAT);
        builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)));

        return builder;
    }

}
