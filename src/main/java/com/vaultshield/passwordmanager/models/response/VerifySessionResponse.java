package com.vaultshield.passwordmanager.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifySessionResponse {
    private String subject;
    private int status;
    private String message;
}
