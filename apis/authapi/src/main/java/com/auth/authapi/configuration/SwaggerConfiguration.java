package com.auth.authapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .addSecurityItem(
                new SecurityRequirement().addList("apiKey").addList("code")
            )
            .components(
                new Components().addSecuritySchemes("apiKey", securityScheme("apiKey"))
            );
    }

    private SecurityScheme securityScheme(String name) {
        return new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name(name);
    }
}
