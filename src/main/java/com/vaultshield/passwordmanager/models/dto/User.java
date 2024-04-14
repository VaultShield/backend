package com.vaultshield.passwordmanager.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
     private String username;
     private String email;
     private String password;
     private String keyWord;
     private String organization;
     private Credentials credentials;
}
