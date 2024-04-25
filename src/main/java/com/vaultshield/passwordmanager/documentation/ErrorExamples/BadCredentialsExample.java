package com.vaultshield.passwordmanager.documentation.ErrorExamples;

import com.vaultshield.passwordmanager.utils.ErrorMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BadCredentialsExample {

    @Schema(description = "Error message", example = ErrorMessages.BAD_CREDENTIALS)
    private String message;
}
