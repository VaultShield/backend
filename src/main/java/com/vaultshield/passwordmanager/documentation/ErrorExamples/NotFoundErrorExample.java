package com.vaultshield.passwordmanager.documentation.ErrorExamples;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NotFoundErrorExample {

    @Schema(description = "Error message", example = ExampleValues.ERROR_NOT_FOUND)
    private String message;
}
