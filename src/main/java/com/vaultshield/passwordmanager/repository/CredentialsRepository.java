package com.vaultshield.passwordmanager.repository;

import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsEntity,String> {
   Optional<List<CredentialsEntity>> findCredentialsEntityByUserId(String userId);
}
