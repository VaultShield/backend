package com.vaultshield.passwordmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<CredentialResponse> toCredentialResponseList(List<CredentialsEntity> credentials) {
        return credentials.stream().map(CredentialMapper::toCredentialResponse).collect(Collectors.toList());
    }
}
