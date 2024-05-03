package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordRequest {

    @Schema(description = "Password", example = ExampleValues.PASSWORD)
    private String password;
    @Schema(description = "New password", example = ExampleValues.NEW_PASSWORD)
    private String newPassword;
}
