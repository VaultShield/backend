package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="password")
@Entity
public class PasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
 //   @Column(name = "credentials_id")
 //   private String credentialsId;
    private String title;
    private String account;
    private String password;
    private String note;
    @OneToOne(targetEntity=CredentialsEntity.class)
    private CredentialsEntity credentials;

}
