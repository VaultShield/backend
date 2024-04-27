package com.vaultshield.passwordmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultshield.passwordmanager.models.entities.SeedPhraseEntity;

@Repository
public interface SeedPhraseRepository extends JpaRepository<SeedPhraseEntity,String>{
    Optional<SeedPhraseEntity> findSeedPhraseEntityByUserId(String uuid);
}
