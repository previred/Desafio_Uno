package cl.prueba.previred.util;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "cl.prueba.util",
        "cl.prueba.sevice.serviceImpl",
        "cl.prueba.model.GDFechas"})
public class SwaggerConfig {

        @Bean
        public Docket produceApi(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("cl.prueba.previred.controller"))
                    .paths(paths())
                    .build();

        }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("GDD")
                    .description("Prueba Previred")
                    .contact("Francisco Venegas")
                    .build();
        }
        private Predicate<String> paths() {
            return Predicates.and(
                    PathSelectors.regex("/gdd.*"),
                    Predicates.not(PathSelectors.regex("/error.*")));

        }
}
