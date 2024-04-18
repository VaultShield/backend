package com.vaultshield.passwordmanager.mapper;

import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.response.UserResponse;

public class UserMapper {

    public static UserResponse toUserResponse(UserEntity user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getActive(),
                user.getUpdateDate(),
                user.getSoftDeleteDate());
    }
}
