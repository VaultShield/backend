package com.vaultshield.passwordmanager.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "password-manager")
public class PasswordManagerProperties {

    private String jwt_key;

    public String getJwt_key() {
        return jwt_key;
    }

    public void setJwt_key(String jwt_key) {
        this.jwt_key = jwt_key;
    }
}
