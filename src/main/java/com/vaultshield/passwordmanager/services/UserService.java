package com.vaultshield.passwordmanager.services;

import com.vaultshield.passwordmanager.exceptions.QueryError;
import com.vaultshield.passwordmanager.exceptions.UserNotFoundException;
import com.vaultshield.passwordmanager.exceptions.UserSaveException;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.UserRequest;

public interface UserService {

    UserEntity getUserById(String id) throws UserNotFoundException;

    UserEntity changeUserData(UserRequest user, String userId) throws UserNotFoundException, UserSaveException;

    UserEntity removeUser(String id) throws UserNotFoundException;

    UserEntity getUserByEmail(String email) throws UserNotFoundException;

    UserEntity getUserByUsername(String username) throws UserNotFoundException;

    UserEntity searchUser(String by, String value) throws UserNotFoundException, QueryError;

}
