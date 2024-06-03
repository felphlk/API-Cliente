package com.test.api.cliente.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 17 and Spring Boot 3.0.1")
                        .version("v1")
                        .description("Client API")
                        .termsOfService("")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://pub.erudio.com.br/meus-cursos")
                        )
                );
    }
}
