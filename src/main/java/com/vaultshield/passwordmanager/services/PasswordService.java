package com.vaultshield.passwordmanager.services;

import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;

public interface PasswordService {

    PasswordEntity createPassword(CredentialRequest password) throws SaveException;

}
