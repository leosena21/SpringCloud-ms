package com.projeto.hruser.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.projeto.hruser"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }


    private ApiInfo metaInfo() {

        @SuppressWarnings("rawtypes")
        ApiInfo apiInfo = new ApiInfo(
                "API REST hr-user",
                "API REST hr-user",
                "1.0",
                "Terms of Service",
                new Contact("Leonardo", "https://github.com/leosena21/",
                        "leeosena21@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html",
                new ArrayList<>()
        );

        return apiInfo;
    }
}