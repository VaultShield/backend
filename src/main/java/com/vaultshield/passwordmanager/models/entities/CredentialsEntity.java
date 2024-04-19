package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name="credentials")
@Entity
@Data
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
