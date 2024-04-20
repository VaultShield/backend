package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PasswordRequest {

    @Schema(example = ExampleValues.PASSWORD)
    private String password;

    @Schema(example = ExampleValues.ACCOUNT)
    private String account;

    @Schema(example = ExampleValues.NOTE)
    private String note;

    @Schema(example = ExampleValues.CRED_TITLE)
    private String title;

}
