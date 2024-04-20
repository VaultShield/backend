package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveException extends RuntimeException {

    private String message;

    public SaveException(String message) {
        this.message = message;
    }
}
