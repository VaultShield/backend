package com.vaultshield.passwordmanager.models.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RecoverNewPasswordResponse {
    String message;
    int status;
}
