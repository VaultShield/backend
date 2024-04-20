package com.vaultshield.passwordmanager.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {
    private String id;
    private String userId;
    private String password;
    private String account;
    private String credentialName;
    private String note;

}
