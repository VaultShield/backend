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

     public String getUsername() {
          return username;
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

     public String getKeyWord() {
          return keyWord;
     }

     public void setKeyWord(String keyWord) {
          this.keyWord = keyWord;
     }

     public String getOrganization() {
          return organization;
     }

     public void setOrganization(String organization) {
          this.organization = organization;
     }

     public Credentials getCredentials() {
          return credentials;
     }

     public void setCredentials(Credentials credentials) {
          this.credentials = credentials;
     }

     private String organization;
     private Credentials credentials;
}
