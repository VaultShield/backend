package com.vaultshield.passwordmanager.models.response;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {

    @Schema(description = "User Token", example = ExampleValues.TOKEN)
    private String token;

    @Schema(description = "Status code", example = ExampleValues.STATUS_200)
    private int status;

    @Schema(description = "Login message", example = ExampleValues.LOGIN_MSG)
    private String message;
}  
