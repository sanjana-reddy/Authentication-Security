package com.unitedtekinfo.authsecurity.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI authenticationAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Authentication & Security API")
                        .version("1.0")
                        .description("JWT Authentication and Role-Based Authorization API")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your@email.com")));
    }
}