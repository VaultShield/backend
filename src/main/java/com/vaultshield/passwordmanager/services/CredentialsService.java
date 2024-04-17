package com.vaultshield.passwordmanager.services;

import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;

public interface CredentialsService {
    void insertCredential(CreateNewCredentialRequest request);
}
