package com.vaultshield.passwordmanager.services.utils;

import org.springframework.http.HttpStatus;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;
import com.vaultshield.passwordmanager.models.response.VerifySessionResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VerifySession {
    final private PasswordManagerProperties properties;

    public VerifySessionResponse verifySession(String header){
        VerifySessionResponse response = new VerifySessionResponse();
        JsonWebToken jwt = new JsonWebToken(properties);

        if (header.isEmpty()){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Authorization header is missing or blank");
            response.setSubject(null);
            return response;
        }

        try {
            String subject = jwt.validateToken(header);

            if (subject.isEmpty()){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
                response.setSubject(null);
                return response;
            }

            response.setStatus(HttpStatus.OK.value());
            response.setMessage(HttpStatus.OK.getReasonPhrase());
            response.setSubject(subject);

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setMessage(e.getMessage());
            response.setSubject(null);
        }
        
        return response;
    }
}
