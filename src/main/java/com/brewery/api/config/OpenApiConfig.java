package com.brewery.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI breweryOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Brewery Management API")
                                .description("REST API for managing brewery recipes and ingredients")
                                .version("1.1.0")
                );
    }
}
