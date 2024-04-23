package com.vaultshield.passwordmanager.models.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
     private String username;

     private String email;

     private String password;

     private List<String> seedPhrase;

     private String organization;

     private Credentials credentials;
}
