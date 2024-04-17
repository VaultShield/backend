package com.vaultshield.passwordmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {                                    
    @Bean
    public OpenAPI api() { 
        return new OpenAPI()
        .info(new Info()
            .title("test"))
        .addSecurityItem(new SecurityRequirement())
        .components(new Components());
    }
}