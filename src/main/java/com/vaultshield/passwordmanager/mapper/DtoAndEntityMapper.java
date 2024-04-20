package com.vaultshield.passwordmanager.mapper;

import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.dto.User;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DtoAndEntityMapper {

    @Autowired
    private UserRepository userRepository;

    public User UserEntitiesToUserDto(UserEntity entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'UserEntitiesToUserDto'");
    }


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

    public CredentialsEntity credentialDtoToCredentialEntity(Credentials credentialsDto){
        CredentialsEntity response = new CredentialsEntity();
      //  PasswordEntity password = new PasswordEntity();
      //  password.setPassword(credentialsDto.getPassword());
      //  password.setAccount(credentialsDto.getAccount());
        response.setCreateDate(LocalDateTime.now());
      //  response.setPassword(password);
        return response;
    }

    public Credentials credentialsEntityToCredentialsDto(CredentialsEntity entity){
        Credentials response = new Credentials();
        response.setAccount(entity.getPassword().getAccount());
        response.setPassword(entity.getPassword().getPassword());
        response.setId(entity.getId());
        return response;
    }

}
