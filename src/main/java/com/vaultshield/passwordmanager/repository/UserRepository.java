package com.vaultshield.passwordmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultshield.passwordmanager.models.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);
}
