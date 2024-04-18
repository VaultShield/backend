package com.vaultshield.passwordmanager.services.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vaultshield.passwordmanager.config.PasswordManagerProperties;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JsonWebToken {

    final private PasswordManagerProperties properties;

    final private static long EXPIRATION_TIME = 900_000;

    public String generateToken(String username) {
        try {
            return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(properties.getJwtKey()));
        } catch (Exception e) {
            System.out.println("error by error generating token: " + e.getMessage());
            throw new IllegalStateException("Internal server error");
        }
    }

    public String validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(properties.getJwtKey())).build();
        try {

            token = token.substring(7);
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Token: %s is not a valid token", token));
        }
    }
}
