package com.drink.dispenser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Configuration for the Swagger
 */
@Configuration
public class SpringFoxConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {

        final Contact contact = new Contact(
                "Developper",
                "",
                "josecoin1989@gmail.com");

        final ApiInfo apiInfo = new ApiInfo(
                "Backend API",
                "This is api for Dispenser APP",
                "0.0.1",
                "",
                contact,
                "",
                "",
                new ArrayList<>());

        final Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .pathMapping("/")
                .apiInfo(ApiInfo.DEFAULT)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .useDefaultResponseMessages(false);

        docket.select()
                .apis(RequestHandlerSelectors.basePackage("com.drink.dispenser.controllers"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }


}
