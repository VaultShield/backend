package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException {

    private String message;

    public UnauthorizedException(String message) {
        this.message = message;
    }
}
