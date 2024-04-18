package com.vaultshield.passwordmanager.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangedCredentialsRequest extends  CreateNewCredentialRequest {
    private String credentialId;

}
