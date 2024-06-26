package com.vaultshield.passwordmanager.security.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.exceptions.TokenRefreshException;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.security.model.RefreshTokenEntity;
import com.vaultshield.passwordmanager.security.repository.RefreshTokenRepository;
import com.vaultshield.passwordmanager.utils.ErrorMessages;

@Service
public class RefreshTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public Optional<RefreshTokenEntity> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenEntity createRefreshToken(String userId) {
        try {
            refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
            RefreshTokenEntity refreshToken = new RefreshTokenEntity();

            refreshToken.setUser(userRepository.findById(userId).get());
            refreshToken.setExpiryDate(Instant.now().plusSeconds(2400));
            refreshToken.setToken(UUID.randomUUID().toString());

            return refreshTokenRepository.save(refreshToken);
        } catch (Exception e) {
            throw new SaveException(e.getMessage());
        }

    }

    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    ErrorMessages.TOKEN_EXPIRED);
        }
        return token;
    }
}
