package com.vaultshield.passwordmanager.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private int status;
    private String message;
}  
