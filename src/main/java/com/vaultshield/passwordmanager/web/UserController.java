package com.vaultshield.passwordmanager.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.Error400EmailExistExample;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.Error400UsernameExistExample;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.NotFoundErrorExample;
import com.vaultshield.passwordmanager.mapper.UserMapper;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.models.response.UserResponse;
import com.vaultshield.passwordmanager.services.impl.UserServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Operation(summary = "Find a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
    })
    @GetMapping("/{id}")
    public UserResponse seeUserById(@PathVariable String id) {
        return UserMapper.toUserResponse(userService.getUserById(id));
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

    @Operation(summary = "Find a user by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
    })
    @GetMapping("/email/{email}")
    public UserResponse seeUserByEmail(@PathVariable String email) {
        return UserMapper.toUserResponse(userService.getUserByEmail(email));
    }

    @Operation(summary = "Find a user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
    })
    @GetMapping("/username/{username}")
    public UserResponse seeUserByUsername(@PathVariable String username) {
        return UserMapper.toUserResponse(userService.getUserByUsername(username));
    }

}
