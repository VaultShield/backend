package com.vaultshield.passwordmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsEntity,String> {

   Optional<List<CredentialsEntity>> findCredentialsEntityByUserId(String userId);
}
