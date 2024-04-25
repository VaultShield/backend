package com.vaultshield.passwordmanager.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.ErrorResponse409Example;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.UnauthorizedErrorExample;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;

import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.models.response.VerifySessionResponse;
import com.vaultshield.passwordmanager.services.impl.AuthImpl;
import com.vaultshield.passwordmanager.services.impl.RecoverImpl;
import com.vaultshield.passwordmanager.services.utils.VerifySession;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    final private AuthImpl service;
    final private PasswordManagerProperties properties;

    @Operation(summary = "User registration", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user was registered correctly", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse409Example.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = service.registerUser(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "User login", description = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content(schema = @Schema(implementation = UnauthorizedErrorExample.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse response = service.login(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
