package com.vaultshield.passwordmanager.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CredentialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private String id;
    private String userId;
    private String credentialTypeid;
    private Date createDate;
    private Date updateDate;
    private Date deletedDate;

    private String state;

    private Boolean favorite;

    private String groupId;

}
