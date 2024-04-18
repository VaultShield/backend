package com.vaultshield.passwordmanager.models.request;

public class ChangedCredentialsRequest extends  CreateNewCredentialRequest {
    private String credentialId;

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }
}
