package com.vaultshield.passwordmanager.services.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.vaultshield.passwordmanager.config.PasswordManagerProperties;

public class JsonWebToken {

    PasswordManagerProperties properties = new PasswordManagerProperties();

    final private static long EXPIRATION_TIME = 900_000;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(properties.getJWTKey()));
    }

    public String validateToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(properties.getJWTKey())).build();
        try {

            token = token.substring(7);
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Token: %s is not a valid token", token));
        }
    }
}
