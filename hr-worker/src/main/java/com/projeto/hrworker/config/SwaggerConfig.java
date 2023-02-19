package com.projeto.hrworker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    private final Response m200 = getResponse("200", "Operação realizada com sucesso");
    private final Response m201 = getResponse("201", "Recurso criado com sucesso");
    private final Response m204 = getResponse("204", "Operação realizada com sucesso, mas não há body");

    private final Response m400 = getResponse("400", "Operação inválida");
    private final Response m401 = getResponse("401", "Acesso não autorizado");
    private final Response m404 = getResponse("404", "Recurso não encontrado");

    private final Response m500 = getResponse("500", "Erro interno ao realizar operação");

    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, Arrays.asList(m200, m400, m404, m500))
                .globalResponses(HttpMethod.POST, Arrays.asList(m201, m400, m404, m500))
                .globalResponses(HttpMethod.PUT, Arrays.asList(m200, m204, m400, m404, m500))
                .globalResponses(HttpMethod.DELETE, Arrays.asList(m204, m400, m401, m404, m500))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.projeto.hrworker"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }


    private ApiInfo metaInfo() {

        @SuppressWarnings("rawtypes")
        ApiInfo apiInfo = new ApiInfo(
                "API REST hr-user",
                "API REST hr-worker",
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

    private Response getResponse(final String code, final String msg){
        return new ResponseBuilder().code(code).description(msg).build();
    }
}
