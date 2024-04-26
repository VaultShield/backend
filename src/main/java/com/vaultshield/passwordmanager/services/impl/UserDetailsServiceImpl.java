package com.vaultshield.passwordmanager.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.utils.ErrorMessages;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                ErrorMessages.USER_NOT_FOUND_BY_USERNAME + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
