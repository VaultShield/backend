package com.vaultshield.passwordmanager.services.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;
import com.vaultshield.passwordmanager.models.entities.SeedPhraseEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.RecoverNewPasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverNewPasswordResponse;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.repository.SeedPhraseRepository;
import com.vaultshield.passwordmanager.services.IRecover;
import com.vaultshield.passwordmanager.services.recover.Recovery;
import com.vaultshield.passwordmanager.services.utils.JsonWebToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecoverImpl implements IRecover {

    final private PasswordManagerProperties properties;

    final private LoginAndRegistrationRepository userRepository;
    final private SeedPhraseRepository seedPhraseRepository;
    private String token;
    private String subject;

    @Override
    public RecoverResponse newRecover(RecoverRequest request) {
        Optional<UserEntity> userEntity;
        Optional<SeedPhraseEntity> seedPhraseEntity;
        RecoverResponse response = new RecoverResponse();
        JsonWebToken jwt = new JsonWebToken(properties);
        

        userEntity = userRepository.findUserEntityByUsername(request.getUsername());
        seedPhraseEntity = seedPhraseRepository.findSeedPhraseEntityByUserId(userEntity.get().getId());

        if (userEntity.isPresent()){
            try {

                if (!Recovery.compareHash(request.seedphrase, seedPhraseEntity.get().getPhrase())){
                    response.setToken(null);
                    response.setMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                }

               token = jwt.generateRecoverToken(userEntity.get().getId());
            } catch (Exception e) {
                response.setToken(null);
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
            response.setToken(token);
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setStatus(HttpStatus.OK.value());
        }

        return response;
    }

    @Override
    public RecoverNewPasswordResponse newPasswordRecover(RecoverNewPasswordRequest request) {
        JsonWebToken jwt = new JsonWebToken(properties);

        RecoverNewPasswordResponse response = new RecoverNewPasswordResponse();
        if (request.getToken().isEmpty()){
            response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        try {
            if (jwt.validateRecoverToken(token)){
                // to do: change password

                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatus(HttpStatus.OK.value());
            }
        } catch (Exception e) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            System.out.println(e);
        }

        return response;
    }
    
}
