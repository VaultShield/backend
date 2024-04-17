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


    public void setId(String id) {
        this.id = id;
    }


    public void setCredentialTypeId(String credentialTypeId) {
        this.credentialTypeId = credentialTypeId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public UserEntity getUser() {
        return user;
    }

    public PasswordEntity getPassword() {
        return password;
    }

    public void setPassword(PasswordEntity password) {
        this.password = password;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

/*    public PasswordEntity getPassword() {
        return password;
    }

    public void setPassword(PasswordEntity password) {
        this.password = password;
    }*/
}
