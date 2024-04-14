package com.vaultshield.passwordmanager.repository;

import com.vaultshield.passwordmanager.models.entities.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAndRegistrationRepository extends JpaRepository<UserEntity,String> {
   Optional<UserEntity> findUserEntityByUsername(String username);
  //  UserEntity getUserFromUsername(String username);
}
