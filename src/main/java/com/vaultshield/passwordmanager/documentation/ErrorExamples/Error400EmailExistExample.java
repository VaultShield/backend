package com.vaultshield.passwordmanager.documentation.ErrorExamples;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Error400EmailExistExample {

    @Schema(description = "Error message", example = ExampleValues.STATUS_400_EMAIL)
    private String message;
}
