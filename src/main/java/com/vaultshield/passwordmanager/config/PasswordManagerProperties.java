package com.vaultshield.passwordmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties(prefix = "password-manager")
public class PasswordManagerProperties {

    private String JWTKey;
}
