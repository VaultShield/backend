package com.vaultshield.passwordmanager.web;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;
import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.models.response.VerifySessionResponse;
import com.vaultshield.passwordmanager.services.impl.LoginAndRegistrationServiceImpl;
import com.vaultshield.passwordmanager.services.utils.VerifySession;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    final private LoginAndRegistrationServiceImpl service;
    final private PasswordManagerProperties properties;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = service.registerUser(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/verifysession")
    public ResponseEntity<?> verifySession(@RequestHeader("Authorization") String header){
        VerifySession verifier = new VerifySession(properties);
        VerifySessionResponse response = verifier.verifySession(header);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    
    // TEST!
    @GetMapping("/dashboard")
    public String dashboard(@RequestHeader String header) {
        return new String();
    }
    

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request){
        ChangePasswordResponse response = service.changePassword(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
