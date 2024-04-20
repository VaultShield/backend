package com.vaultshield.passwordmanager.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.QueryError;
import com.vaultshield.passwordmanager.exceptions.UserNotFoundException;
import com.vaultshield.passwordmanager.exceptions.UserSaveException;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity getUserById(String id) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found");

        return user.get();
    }

    @Override
    public UserEntity changeUserData(UserRequest user, String userId) throws UserNotFoundException, UserSaveException {
        Optional<UserEntity> userToChange = userRepository.findById(userId);
        if (!userToChange.isPresent())
            throw new UserNotFoundException("User not found");
        if (!userToChange.get().getUsername().equals(user.getUsername())
                && userRepository.existsByUsername(user.getUsername()))
            throw new UserSaveException("Username already exists");
        if (!userToChange.get().getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail()))
            throw new UserSaveException("Email already exists");
        UserEntity existingUser = userToChange.get();
        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());

        if (user.getUsername() != null)
            existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public UserEntity removeUser(String id) throws UserNotFoundException {
        Optional<UserEntity> userToDelete = userRepository.findById(id);
        if (!userToDelete.isPresent())
            throw new UserNotFoundException("User not found");
        userRepository.deleteById(id);
        return userToDelete.get();
    }

    @Override
    public UserEntity getUserByEmail(String email) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found - email does not exists");

        return user.get();
    }

    @Override
    public UserEntity getUserByUsername(String username) throws UserNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent())
            throw new UserNotFoundException("User not found - username does not exists");

        return user.get();
    }

    @Override
    public UserEntity searchUser(String by, String value) throws UserNotFoundException, QueryError {
        UserEntity user = null;
        switch (by) {
            case "id":
                user = getUserById(value);
                break;
            case "email":
                user = getUserByEmail(value);
                break;
            case "username":
                user = getUserByUsername(value);
                break;
            default:
                throw new QueryError("Incorrect query. Use 'id', 'username' o 'email'.");
        }
        return user;
    }
}
