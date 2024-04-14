package com.vaultshield.passwordmanager.web;

import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.services.impl.LoginAndRegistrationServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    final private LoginAndRegistrationServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = service.registerUser(request);
        return ResponseEntity.status(response.getStatus()).body(response.getId());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        return ResponseEntity.status(response.getStatus()).body(response.getToken());
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        ChangePasswordResponse response = service.changePassword(request);
        return ResponseEntity.status(response.getStatus()).body(null);
    }
    
}
