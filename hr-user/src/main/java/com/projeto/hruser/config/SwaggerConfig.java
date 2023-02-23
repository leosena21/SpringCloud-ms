package com.projeto.hruser.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class SwaggerConfig {

    private final String m200 = "Operação realizada com sucesso";
    private final String m201 = "Recurso criado com sucesso";
    private final String m204 = "Operação realizada com sucesso, mas não há body";

    private final String m400 = "Operação inválida";
    private final String m401 = "Acesso não autorizado";
    private final String m404 = "Recurso não encontrado";

    private final String m500 = "Erro interno ao realizar operação";

    @Bean
    public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                if (!Objects.isNull(pathItem.getPost())) {
                    operation.getResponses().remove("200");
                    ApiResponses apiResponses = operation.getResponses();
                    apiResponses.addApiResponse("201", getResponse(m201));
                    apiResponses.addApiResponse("400", getResponse(m400));
                    apiResponses.addApiResponse("500", getResponse(m500));
                    setContentMediaType(operation);
                }
                if(!Objects.isNull(pathItem.getPut())){
                    ApiResponses apiResponses = operation.getResponses();
                    apiResponses.addApiResponse("200", getResponse(m200));
                    apiResponses.addApiResponse("204", getResponse(m204));
                    apiResponses.addApiResponse("400", getResponse(m400));
                    apiResponses.addApiResponse("500", getResponse(m500));
                    setContentMediaType(operation);
                }
                if(!Objects.isNull(pathItem.getGet())){
                    ApiResponses apiResponses = operation.getResponses();
                    apiResponses.addApiResponse("400", getResponse(m400));
                    apiResponses.addApiResponse("500", getResponse(m500));
                    setContentMediaType(operation);
                }
                if(!Objects.isNull(pathItem.getDelete())){
                    ApiResponses apiResponses = operation.getResponses();
                    apiResponses.addApiResponse("204", getResponse(m204));
                    apiResponses.addApiResponse("400", getResponse(m400));
                    apiResponses.addApiResponse("404", getResponse(m404));
                    apiResponses.addApiResponse("500", getResponse(m500));
                }
            }));
        };
    }

    private void setContentMediaType(Operation operation){
        operation.getResponses().forEach((s, apiResponse) -> {
            Content content = apiResponse.getContent();
            if(!content.isEmpty()){
                Content newContent = new Content();
                content.forEach((s1, mediaType) -> {
                    if(StringUtils.isNotBlank(s1) && !s1.equalsIgnoreCase("application/json")){
                        newContent.addMediaType("application/json", mediaType);
                    }
                    newContent.addMediaType(s1, mediaType);
                });
                apiResponse.setContent(newContent);
            }
        });
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("hr-user API")
                        .description("ms hr-user")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("hr-user Wiki Documentation")
                        .url("https://docs.spring.io"));
    }

    private ApiResponse getResponse(final String msg){
        if(msg.equalsIgnoreCase(m201)){
            return new ApiResponse().description(msg).content(new Content());
        }
        return new ApiResponse().description(msg)
                .content(new Content()
                        .addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, new MediaType()));
    }
}
