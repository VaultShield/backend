package com.vaultshield.passwordmanager.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseEntity<RecoverResponse> recover(RecoverRequest request) {
    try {
        
        Optional<UserEntity> userEntity;
        Optional<SeedPhraseEntity> seedPhraseEntity;
        JwtTokenUtil jwt = new JwtTokenUtil();
        
        userEntity = userRepository.findByUsername(request.getUsername());
        seedPhraseEntity = seedPhraseRepository.findSeedPhraseEntityByUserId(userEntity.get().getId());

        if (userEntity.isPresent() && seedPhraseEntity.isPresent()){
                if (Recovery.compareHash(request.seedphrase, seedPhraseEntity.get().getPhrase())){
                    try {
                        String token = jwt.generateRecoverToken(userEntity.get().getId());

                        if (token.isEmpty()){
                            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                        }

                        return new ResponseEntity<>(
                        new RecoverResponse(token),
                        HttpStatus.OK);
                    } catch (Exception e) {
                        return new ResponseEntity<>(
                        HttpStatus.INTERNAL_SERVER_ERROR);
                    }
            }else {
                return new ResponseEntity<>(
                        new RecoverResponse(null),
                        HttpStatus.UNAUTHORIZED);
            }
        }else {
            return new ResponseEntity<>(
                        new RecoverResponse(null),
                        HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        System.out.println(e);
        return null;
    }
    }

    @Override
    public ResponseEntity<?> recoverchange(RecoverChangePasswordRequest request, String header) {
        JwtTokenUtil jwt = new JwtTokenUtil();
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