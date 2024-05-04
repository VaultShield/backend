package com.vaultshield.passwordmanager.controller;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.*;
import com.vaultshield.passwordmanager.mapper.UserMapper;
import com.vaultshield.passwordmanager.models.request.ChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.models.response.ChangePasswordResponse;
import com.vaultshield.passwordmanager.models.response.UserResponse;
import com.vaultshield.passwordmanager.services.impl.UserServiceImpl;
import com.vaultshield.passwordmanager.utils.ErrorMessages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "Update user information")
@RestController
@RequestMapping("/api/user")
public class UserController {

        @Autowired
        private UserServiceImpl userService;

        @Operation(summary = "Search a user by id/email/username")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                        @ApiResponse(responseCode = "404", description = ErrorMessages.USER_NOT_FOUND, content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class))),
                        @ApiResponse(responseCode = "401", description = ErrorMessages.BAD_CREDENTIALS, content = @Content(schema = @Schema(implementation = BadCredentialsExample.class)))
        })
        @GetMapping("/search")
        public UserResponse searchUser(
                        @Parameter(schema = @Schema(allowableValues = { "id",
                                        "email", "username" })) @RequestParam(name = "by") String by,
                        @RequestParam(name = "value") String value) {
                return UserMapper.toUserResponse(userService.searchUser(by, value));
        }

        @Operation(summary = "Update an existing user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class))),
                        @ApiResponse(responseCode = "400", description = "Username already exists", content = @Content(schema = @Schema(implementation = Error400UsernameExistExample.class))),
                        @ApiResponse(responseCode = "400", description = "Email already exists", content = @Content(schema = @Schema(implementation = Error400EmailExistExample.class)))
        })
        @PutMapping("/{id}")
        public UserResponse updateUser(@RequestBody UserRequest user,
                        @PathVariable String id) {
                return UserMapper.toUserResponse(userService.changeUserData(user, id));
        }

        @Operation(summary = "Update User Password")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(schema = @Schema(implementation = UserResponse.class))),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class))),
                        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = UnauthorizedErrorExample.class)))
        })
        @PutMapping("/password/{id}")
        public ResponseEntity<ChangePasswordResponse> changeUserPassword(
                @Valid @RequestBody @Schema(implementation = ChangePasswordRequest.class)
                        ChangePasswordRequest request,
                        @PathVariable String id) {
                return userService.changeUserPassword(request, id);
        }

        @Operation(summary = "Delete an existing user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Deleted successfully", content = @Content),
                        @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
        })
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteUser(@PathVariable String id) {
                userService.removeUser(id);
        }

}
