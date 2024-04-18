package com.vaultshield.passwordmanager.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {
    private String username;
    private String password;

    @JsonProperty("new_password")
    private String newPassword;

}
