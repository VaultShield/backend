package com.vaultshield.passwordmanager.documentation.ErrorExamples;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse409Example {

    @Schema(description = "Login message", example = ExampleValues.STATUS_409_MSG)
    private String message;
}
