package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConflictException extends RuntimeException {
    private String message;

    public ConflictException(String message) {
        this.message = message;
    }
}
