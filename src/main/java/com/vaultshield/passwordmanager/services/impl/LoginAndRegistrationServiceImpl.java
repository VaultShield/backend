package com.vaultshield.passwordmanager.services.impl;

import com.vaultshield.passwordmanager.mapper.DtoAndEntityMapper;
import com.vaultshield.passwordmanager.models.dto.User;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.services.LoginAndRegistrationService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service

public class LoginAndRegistrationServiceImpl implements LoginAndRegistrationService {

    @Autowired
      private LoginAndRegistrationRepository repository;
    @Autowired
    private DtoAndEntityMapper mapper;

    @Override
    public RegisterResponse registerUser(RegisterRequest user) {
      UserEntity userEntity = new UserEntity();
      User userDto = new User();
      RegisterResponse response = new RegisterResponse();

      userDto.setUsername(user.getUsername());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(user.getPassword());

      userEntity = mapper.userDtoToUserEntity(userDto);
      repository.save(userEntity);

      response.setId(userEntity.getId());
      response.setStatus(HttpStatus.OK.value());
      return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
      Optional<UserEntity> userEntity;
      LoginResponse response = new LoginResponse();
      userEntity = repository.findUserEntityByUsername (request.getUsername());
      if (userEntity.isPresent()){
          if (!userEntity.get().getPassword().equals(request.getPassword())) {
            response.setToken(null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
          }else {
            response.setToken(null);
            response.setStatus(HttpStatus.OK.value());
          }
      }else {
        response.setStatus(HttpStatus.NOT_FOUND.value());
      }
      return response;
    }

    @Override
    public void verifyUser(LoginRequest request) {
     //map    User user =  repository.getUserFromUsername(request.username);
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) {
      ChangePasswordResponse response = new ChangePasswordResponse();
      Optional<UserEntity> userEntityOpt = repository.findUserEntityByUsername(request.getUsername());

      if (userEntityOpt.isPresent()){
        UserEntity userEntity = userEntityOpt.get();
          if (!request.getPassword().equals(userEntity.getPassword())){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
          }else {
            userEntity.setPassword(request.getNewPassword());
            repository.save(userEntity);
            response.setStatus(HttpStatus.OK.value());
          }
      }else {
        response.setStatus(HttpStatus.NOT_FOUND.value());
      }
      return response;
    }
}
