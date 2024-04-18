package com.vaultshield.passwordmanager.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "password-manager", name= "enable")
public class PasswordManagerConfig {
    @Bean
    @ConfigurationProperties(prefix = "password-manager")
    PasswordManagerProperties properties(){
        return new PasswordManagerProperties();
    }
}
