package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSaveException extends RuntimeException {

    private String message;

    public UserSaveException(String message) {
        this.message = message;
    }
}
