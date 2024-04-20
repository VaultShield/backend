package com.vaultshield.passwordmanager.models.response;

import java.time.LocalDateTime;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialResponse {

    @Schema(description = "Credential ID", example = ExampleValues.ID)
    private String id;

    @Schema(description = "Credential type", example = ExampleValues.CRED_TYPE)
    private String credentialType;

    @Schema(description = "Create date", example = ExampleValues.UPDATE_DATE)
    private LocalDateTime createDate;

    @Schema(description = "Update date", example = ExampleValues.UPDATE_DATE)
    private LocalDateTime updateDate;

    @Schema(description = "Delete date", example = ExampleValues.SOFT_DELETE_DATE)
    private LocalDateTime deletedDate;

    @Schema(description = "Credential state", example = ExampleValues.CRED_STATE)
    private String state;

    @Schema(description = "Favourite?", example = ExampleValues.CRED_FAV)
    private Boolean favourite;

    @Schema(description = "Group ID", example = ExampleValues.CRED_GROUP)
    private String gtoupId;

    @Schema(description = "User ID", example = ExampleValues.ID)
    private String userId;

    @Schema(description = "Password ID", example = ExampleValues.ID)
    private String passwordId;

}
