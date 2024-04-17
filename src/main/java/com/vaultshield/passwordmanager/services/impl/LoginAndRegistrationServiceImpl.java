package com.vaultshield.passwordmanager.services.impl;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;
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
import com.vaultshield.passwordmanager.services.utils.JsonWebToken;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginAndRegistrationServiceImpl implements LoginAndRegistrationService {

    final private PasswordManagerProperties properties;

    public String token;
    final private LoginAndRegistrationRepository repository;
    final private DtoAndEntityMapper mapper;
    final private BCryptPasswordEncoder bcrypt;

    @Override
    public RegisterResponse registerUser(RegisterRequest user) {
      UserEntity userEntity = new UserEntity();
      User userDto = new User();
      RegisterResponse response = new RegisterResponse();

      userDto.setUsername(user.getUsername());
      userDto.setEmail(user.getEmail());
      userDto.setPassword(bcrypt.encode(user.getPassword()));

      userEntity = mapper.userDtoToUserEntity(userDto);
      try {
        repository.save(userEntity);

        response.setId(userEntity.getId());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());

      } catch (DataIntegrityViolationException e) {

        response.setId(null);
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage("The email or username is already in use.");
      } catch (Exception e) {

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Internal server error occurred.");
    }

      return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
      Optional<UserEntity> userEntity;
      LoginResponse response = new LoginResponse();
      JsonWebToken jwt = new JsonWebToken(properties);

      userEntity = repository.findUserEntityByUsername (request.getUsername());
      if (userEntity.isPresent() && bcrypt.matches(request.getPassword(), userEntity.get().getPassword())){
          
          try {
            token = jwt.generateToken(userEntity.get().getUsername());

            if (token.isEmpty()){
              throw new Error();
            }
          } catch (Exception e) {
            response.setToken(null);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
          }

            response.setToken(token);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());

      }else{
            response.setToken(null);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
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

      if (userEntityOpt.isPresent() && bcrypt.matches(request.getPassword(), userEntityOpt.get().getPassword())){
        UserEntity userEntity = userEntityOpt.get();

        userEntity.setPassword(request.getNewPassword());
        repository.save(userEntity);

        response.setStatus(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.getReasonPhrase());
      }else {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
      }
      return response;
    }
}
