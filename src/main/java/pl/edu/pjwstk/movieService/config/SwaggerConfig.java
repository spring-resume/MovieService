package pl.edu.pjwstk.movieService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket atividadeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.edu.pjwstk.MovieService"))
                .paths(PathSelectors.any())
                .build().apiInfo(myApiInfo());

    }

    private ApiInfo myApiInfo() {
        return new ApiInfo(
                "MovieService REST API",
                "MovieService REST API",
                "v1.00",
                "MovieService",
                new Contact("Jakub Słomiński", "https://www.example.com", "jakub.slominski.job@gmail"),
                "my own LicenseX123", "www.example123.com", Collections.emptyList());

    }
}
