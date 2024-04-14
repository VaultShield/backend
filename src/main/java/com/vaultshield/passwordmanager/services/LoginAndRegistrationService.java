package com.vaultshield.passwordmanager.services;


import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;

public interface LoginAndRegistrationService {
    RegisterResponse registerUser(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    void verifyUser(LoginRequest request);
    ChangePasswordResponse changePassword(ChangePasswordRequest request);
}
