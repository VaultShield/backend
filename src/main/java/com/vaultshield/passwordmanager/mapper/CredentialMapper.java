package com.vaultshield.passwordmanager.mapper;

import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.response.CredentialResponse;

public class CredentialMapper {

    public static CredentialResponse toCredentialResponse(CredentialsEntity credential) {
        return new CredentialResponse(
                credential.getId(),
                credential.getCredentialTypeId(),
                credential.getCreateDate(),
                credential.getUpdateDate(),
                credential.getDeletedDate(),
                credential.getState(),
                credential.getFavorite(),
                credential.getGroupId(),
                credential.getUser().getId(),
                credential.getPassword().getId());
    }
}
