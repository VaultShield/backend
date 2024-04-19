package com.vaultshield.passwordmanager.models.response;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterResponse {

    @Schema(description = "User ID", example = ExampleValues.ID)
    private String id;

    @Schema(description = "Status code", example = ExampleValues.STATUS_200)
    private int status;

    @Schema(description = "Login message", example = ExampleValues.LOGIN_MSG)
    private String message;
}
