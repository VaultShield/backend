package com.vaultshield.passwordmanager.mapper;

import com.vaultshield.passwordmanager.models.dto.User;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface EntitiesMapper {

    // Mapper from User to UserEntitiy, utility
    User UserEntitiesToUserDto(UserEntity entity);

    // Mapper from UserDTO to UserEntity, utility put/post
    UserEntity userDtoToUserEntity(User dto);
}
