package com.vaultshield.passwordmanager.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.ErrorResponse409Example;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.UnauthorizedErrorExample;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "User registration", description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was registered correctly", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterResponse.class))
            }),
            @ApiResponse(responseCode = "409", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse409Example.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(
            @Valid @RequestBody @Schema(implementation = RegisterRequest.class) RegisterRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @Operation(summary = "User login", description = "User authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))
            }),
            @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content(schema = @Schema(implementation = UnauthorizedErrorExample.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @RequestBody @Validated @Schema(implementation = LoginRequest.class) LoginRequest loginRequest) {
        return userService.loginUser((loginRequest));
    }

}
