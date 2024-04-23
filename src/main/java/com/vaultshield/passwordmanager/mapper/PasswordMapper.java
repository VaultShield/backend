package com.vaultshield.passwordmanager.mapper;

import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.response.PasswordResponse;

public class PasswordMapper {

    public static PasswordResponse toPasswordResponse(PasswordEntity password) {
        return new PasswordResponse(
                password.getId(),
                password.getTitle(),
                password.getAccount(),
                password.getPassword(),
                password.getNote());
    }
}
