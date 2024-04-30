package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="seedphrase")
@Entity
@Getter
@Setter
public class SeedPhraseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    String id;

    @Column(nullable = false)
    private String userId;

    @Column(name = "phrase", nullable = false)
    private String phrase; // hash
}
