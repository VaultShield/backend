package com.vaultshield.passwordmanager.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewCredentialRequest {
    private String userId;
    private String account;
    private String value;
    private String note;
    private String title;
    private String password;

   
}
