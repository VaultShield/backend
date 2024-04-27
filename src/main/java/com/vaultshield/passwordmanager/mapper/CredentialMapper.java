package com.vaultshield.passwordmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.response.CredentialResponse;
import com.vaultshield.passwordmanager.models.response.PasswordResponse;

public class CredentialMapper {

    public static CredentialResponse toCredentialResponse(CredentialsEntity credential) {
        PasswordResponse passwordResponse = null;
        if (credential.getPassword() != null) {
            PasswordEntity passwordEntity = credential.getPassword();
            passwordResponse = PasswordMapper.toPasswordResponse(passwordEntity);
        }
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
                passwordResponse);
    }

    public static List<CredentialResponse> toCredentialResponseList(List<CredentialsEntity> credentials) {
        return credentials.stream().map(CredentialMapper::toCredentialResponse).collect(Collectors.toList());
    }
}
