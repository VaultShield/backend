package com.vaultshield.passwordmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PasswordManagerProperties.class)
    public class PasswordManagerConfig {

        @Bean
        @ConfigurationProperties(prefix = "password-manager")
        PasswordManagerProperties properties(){
            return new PasswordManagerProperties();
        }
}
