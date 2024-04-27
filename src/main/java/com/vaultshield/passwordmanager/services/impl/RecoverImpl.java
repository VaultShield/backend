package com.vaultshield.passwordmanager.services.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.models.entities.SeedPhraseEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.RecoverChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;
import com.vaultshield.passwordmanager.repository.SeedPhraseRepository;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.security.jwt.JwtTokenUtil;
import com.vaultshield.passwordmanager.security.service.Recovery;
import com.vaultshield.passwordmanager.services.Recover;
import com.vaultshield.passwordmanager.utils.ErrorMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecoverImpl implements Recover {
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeedPhraseRepository seedPhraseRepository;

    @Autowired
    private JwtTokenUtil jwt;

    @Override
    public ResponseEntity<RecoverResponse> recover(RecoverRequest request) {
        Optional<UserEntity> userEntity;
        Optional<SeedPhraseEntity> seedPhraseEntity;

        userEntity = userRepository.findByUsername(request.getUsername());
        if (userEntity == null || !userEntity.isPresent()){
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND_BY_USERNAME + request.username);
        }

        seedPhraseEntity = seedPhraseRepository.findSeedPhraseEntityByUserId(userEntity.get().getId());
        if (seedPhraseEntity == null || !seedPhraseEntity.isPresent()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        String token = jwt.generateRecoverToken(userEntity.get().getId());
        if (token.isEmpty()){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (Recovery.compareHash(request.seedphrase, seedPhraseEntity.get().getPhrase())){
            return new ResponseEntity<>(
                new RecoverResponse(token),
               HttpStatus.OK);
        }else {
            return new ResponseEntity<>(
            new RecoverResponse(null),
            HttpStatus.UNAUTHORIZED);
        }
}

    @Override
    public ResponseEntity<?> recoverchange(RecoverChangePasswordRequest request, String header) {
        Optional<UserEntity> userEntity;

        if (header.isEmpty() || request.getNewPassword().isEmpty() ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            String subject = jwt.validateRecoverToken(header);
            userEntity = userRepository.findById(subject);
            UserEntity newUserEntity = userEntity.get();

            String passwordHashed = bcrypt.encode(request.getNewPassword());
            newUserEntity.setPassword(passwordHashed);

            newUserEntity = userRepository.save(newUserEntity);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}