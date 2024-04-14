package com.vaultshield.passwordmanager.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.vaultshield.passwordmanager.models.dto.User;
import com.vaultshield.passwordmanager.models.entities.UserEntity;

@Component
public class DtoAndEntityMapper implements EntitiesMapper{

    @Override
    public User UserEntitiesToUserDto(UserEntity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UserEntitiesToUserDto'");
    }

    @Override
    public UserEntity userDtoToUserEntity(User userDto) {
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        String username = userDto.getUsername();
        if (password == "" || email == "" || username == ""){
            // TO DO error handler ?
            throw new UnsupportedOperationException("Unimplemented method 'UserEntitiesToUserDto'");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        userEntity.setActive(true);
        userEntity.setUpdateDate(LocalDateTime.now());

        return userEntity;
    }

}
