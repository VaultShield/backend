package com.vaultshield.passwordmanager.models.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.vaultshield.passwordmanager.security.model.RefreshTokenEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Boolean active;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<CredentialsEntity> credentials;
    
    private LocalDateTime softDeleteDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RefreshTokenEntity> refreshTokens;

}
