package com.vaultshield.passwordmanager.services;

import java.util.List;

import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;

public interface CredentialsService {

    CredentialsEntity insertCredential(CredentialRequest request) throws SaveException;

    CredentialsEntity modifyCredential(CredentialRequest request, String id) throws SaveException, NotFoundException;

    CredentialsEntity deleteCredential(String id) throws NotFoundException;

    List<CredentialsEntity> findAllCredentials(String userId) throws NotFoundException;

    CredentialsEntity findOneCredential(String id) throws NotFoundException;

}
