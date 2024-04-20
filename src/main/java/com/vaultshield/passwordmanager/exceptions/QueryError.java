package com.vaultshield.passwordmanager.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QueryError extends RuntimeException {
    private String message;

    public QueryError(String message) {
        this.message = message;
    }
}
