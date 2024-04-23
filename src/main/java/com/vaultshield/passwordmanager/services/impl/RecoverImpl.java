package com.vaultshield.passwordmanager.services.impl;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.services.IRecover;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecoverImpl implements IRecover {

    final private LoginAndRegistrationRepository repository;
    final private BCryptPasswordEncoder bcrypt;

    @Override
    public RecoverResponse newRecover(RecoverRequest request) {
        Optional<UserEntity> userEntity;
        RecoverResponse response = new RecoverResponse();
        

        userEntity = repository.findUserEntityByUsername(request.getUsername());
        if (userEntity.isPresent() && request.getSeedphrase().equals(userEntity.get().getSeedPhrase())){
            UserEntity userChanged = userEntity.get();
        }

        throw new UnsupportedOperationException("Unimplemented method 'newRecover'");
    }
    
}
