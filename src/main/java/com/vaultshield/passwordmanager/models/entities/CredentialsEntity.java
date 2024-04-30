package com.vaultshield.passwordmanager.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name="credentials")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @PrimaryKeyJoinColumn
    private String id;

    private String credentialTypeId;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime deletedDate;

    private String state;

    private Boolean favorite;

    private String groupId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @OneToOne(targetEntity=PasswordEntity.class,cascade=CascadeType.ALL)
    private PasswordEntity password;

}
