package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    private Boolean active;

    private LocalDateTime updateDate;

    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private Set<CredentialsEntity> credentials;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Set<CredentialsEntity> getCredentials() {
        return credentials;
    }

    public void setCredentials(Set<CredentialsEntity> credentials) {
        this.credentials = credentials;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getSoftDeleteDate() {
        return softDeleteDate;
    }

    public void setSoftDeleteDate(LocalDateTime softDeleteDate) {
        this.softDeleteDate = softDeleteDate;
    }

    private LocalDateTime softDeleteDate;
}
