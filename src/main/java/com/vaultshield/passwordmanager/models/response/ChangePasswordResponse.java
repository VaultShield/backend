package com.vaultshield.passwordmanager.models.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordResponse {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
