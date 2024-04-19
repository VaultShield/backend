package com.vaultshield.passwordmanager.documentation.ErrorExamples;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UnauthorizedErrorExample {

    @Schema(description = "User ID", example = ExampleValues.ID_NULL)
    private String id;

    @Schema(description = "Status code", example = ExampleValues.STATUS_401)
    private int status;

    @Schema(description = "Login message", example = ExampleValues.STATUS_401_MSG)
    private String message;
}
