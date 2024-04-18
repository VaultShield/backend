package com.vaultshield.passwordmanager.services;

import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.request.ChangedCredentialsRequest;
import com.vaultshield.passwordmanager.models.request.CommonIdRequest;
import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;
import com.vaultshield.passwordmanager.models.request.FindCredentialsRequest;

import java.util.List;

public interface CredentialsService {
    void insertCredential(CreateNewCredentialRequest request);
    void modifyCredential(ChangedCredentialsRequest request);

    void deleteCredential(CommonIdRequest request);

     List<Credentials> findAllCredentials(FindCredentialsRequest request);

     Credentials findOneCredential();

}
