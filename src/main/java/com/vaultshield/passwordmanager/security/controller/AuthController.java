package com.vaultshield.passwordmanager.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.ErrorResponse409Example;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.UnauthorizedErrorExample;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RecoverChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.security.model.request.TokenRefreshRequest;
import com.vaultshield.passwordmanager.security.model.response.TokenRefreshResponse;
import com.vaultshield.passwordmanager.services.impl.RecoverImpl;
import com.vaultshield.passwordmanager.services.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth", description = "Register, Login and Refresh Token")
public class AuthController {

        @Autowired
        private UserServiceImpl userService;
        private RecoverImpl seedService;

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

        @Operation(summary = "Refresh token", description = "Refresh token")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Ok", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TokenRefreshResponse.class))
                        }),
                        @ApiResponse(responseCode = "401", description = "unauthorized", content = @Content(schema = @Schema(implementation = UnauthorizedErrorExample.class))),
        })
        
        @PostMapping("/refreshtoken")
        public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
                return userService.refreshtoken(request);
        }

        @PostMapping("/recover")
        public ResponseEntity<RecoverResponse> recover(
                @Valid @RequestBody @Schema(implementation = RecoverRequest.class) RecoverRequest request) {
                return seedService.recover(request);
        }

        @PutMapping("/recover")
        public ResponseEntity<?> putrecover(@RequestBody RecoverChangePasswordRequest request, @RequestHeader("Recover") String header) {
                return seedService.recoverchange(request, header);
        }
}
