package com.vaultshield.passwordmanager.services;

import java.util.List;

import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.request.ChangedCredentialsRequest;
import com.vaultshield.passwordmanager.models.request.CommonIdRequest;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;

public interface CredentialsService {

    CredentialsEntity insertCredential(CredentialRequest request) throws SaveException;

    void modifyCredential(ChangedCredentialsRequest request);

    void deleteCredential(CommonIdRequest request);

    List<CredentialsEntity> findAllCredentials(String userId) throws NotFoundException;

    Credentials findOneCredential(CommonIdRequest request);

}
