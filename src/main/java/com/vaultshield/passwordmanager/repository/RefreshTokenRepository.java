package com.vaultshield.passwordmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.vaultshield.passwordmanager.models.entities.RefreshTokenEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {

    Optional<RefreshTokenEntity> findByToken(String token);

    @Modifying
    int deleteByUser(UserEntity user);
}
