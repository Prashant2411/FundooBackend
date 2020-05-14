package com.bridgelabz.fundoo.configuration;

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

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.fundoo"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Fundoo API",
                "Fundoo is a note-taking service developed by Clark Kent." +
                        " Launched on June 20, 2020, Fundoo is available on the web at www.fundoo.com.",
                "1.0",
                "http://www.fundoo.com",
                new Contact("Fundoo", "http://www.fundoo.com", "contactus@fundoo.com"),
                "License of API", "http://www.fundoo.com", Collections.emptyList());
    }
}
