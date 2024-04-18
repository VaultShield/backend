package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {

    private String message;

    public UserNotFoundException(String message) {
        this.message = message;
    }
}
