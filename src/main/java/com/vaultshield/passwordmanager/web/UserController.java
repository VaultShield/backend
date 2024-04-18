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

import com.vaultshield.passwordmanager.mapper.UserMapper;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.models.response.UserResponse;
import com.vaultshield.passwordmanager.services.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public UserResponse seeUserById(@PathVariable String id) {
        return UserMapper.toUserResponse(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody UserRequest user,
            @PathVariable String id) {
        return UserMapper.toUserResponse(userService.changeUserData(user, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(id);
    }

    @GetMapping("/email/{email}")
    public UserResponse seeUserByEmail(@PathVariable String email) {
        return UserMapper.toUserResponse(userService.getUserByEmail(email));
    }

    @GetMapping("/username/{username}")
    public UserResponse seeUserByUsername(@PathVariable String username) {
        return UserMapper.toUserResponse(userService.getUserByUsername(username));
    }

}
