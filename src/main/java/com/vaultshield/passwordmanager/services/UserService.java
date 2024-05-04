package com.vaultshield.passwordmanager.services;

import org.springframework.http.ResponseEntity;

import com.vaultshield.passwordmanager.exceptions.ConflictException;
import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.exceptions.QueryError;
import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.security.model.request.TokenRefreshRequest;

public interface UserService {

    ResponseEntity<RegisterResponse> registerUser(RegisterRequest signUpRequest) throws ConflictException;

    ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) throws NotFoundException;

    ResponseEntity<?> refreshtoken(TokenRefreshRequest request);

    UserEntity getUserById(String id) throws NotFoundException;

    UserEntity changeUserData(UserRequest user, String userId) throws NotFoundException, SaveException;

    ResponseEntity<ChangePasswordResponse> changeUserPassword(ChangePasswordRequest request, String id) throws NotFoundException, SaveException;

    UserEntity removeUser(String id) throws NotFoundException;

    UserEntity getUserByEmail(String email) throws NotFoundException;

    UserEntity getUserByUsername(String username) throws NotFoundException;

    UserEntity searchUser(String by, String value) throws NotFoundException, QueryError;

}
