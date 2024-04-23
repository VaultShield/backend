package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name="users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = {"email"})
})
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    
    private List<String> seedPhrase;

    private Boolean active;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<CredentialsEntity> credentials;
    
    private LocalDateTime softDeleteDate;
}
