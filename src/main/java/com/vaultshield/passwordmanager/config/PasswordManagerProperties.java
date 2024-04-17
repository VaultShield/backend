package com.vaultshield.passwordmanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties(prefix = "password-manager")
@Validated
public class PasswordManagerProperties {

    private String jwtKey;
}
