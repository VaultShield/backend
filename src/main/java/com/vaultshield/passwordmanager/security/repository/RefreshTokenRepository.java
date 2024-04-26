package com.vaultshield.passwordmanager.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.security.model.RefreshTokenEntity;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, String> {

    Optional<RefreshTokenEntity> findByToken(String token);

    void deleteByUser(UserEntity user);
}
