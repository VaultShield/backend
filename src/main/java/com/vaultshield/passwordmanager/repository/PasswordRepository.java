package com.vaultshield.passwordmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultshield.passwordmanager.models.entities.PasswordEntity;

@Repository
public interface PasswordRepository extends JpaRepository<PasswordEntity, String> {

}
