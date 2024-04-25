package com.vaultshield.passwordmanager.models.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RecoverResponse {
    private int status;
    private String message;
    private String token;
}
