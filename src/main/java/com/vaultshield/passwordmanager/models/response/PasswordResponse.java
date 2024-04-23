package com.vaultshield.passwordmanager.models.response;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResponse {

    @Schema(description = "Password ID", example = ExampleValues.PASS_ID)
    private String id;

    @Schema(description = "Password title", example = ExampleValues.CRED_TITLE)
    private String title;

    @Schema(description = "User account for this password", example = ExampleValues.ACCOUNT)
    private String account;

    @Schema(description = "Password", example = ExampleValues.PASSWORD)
    private String password;

    @Schema(description = "Password note", example = ExampleValues.NOTE)
    private String note;
}