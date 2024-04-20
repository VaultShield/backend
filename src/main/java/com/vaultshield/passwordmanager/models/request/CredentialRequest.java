package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;
import com.vaultshield.passwordmanager.models.dto.CredentialType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class CredentialRequest {
    @Schema(example = ExampleValues.ID)
    private String userId;

    @Schema(example = ExampleValues.CRED_TYPE)
    private CredentialType credentialType;

    @Schema(example = ExampleValues.CRED_FAV)
    private Boolean favorite;

    @Schema(example = ExampleValues.CRED_GROUP)
    private String groupId;

    @Schema(example = ExampleValues.PASSWORD)
    private String password;

    @Schema(example = ExampleValues.ACCOUNT)
    private String account;

    @Schema(example = ExampleValues.NOTE)
    private String note;

    @Schema(example = ExampleValues.CRED_TITLE)
    private String title;

}
