package com.vaultshield.passwordmanager.models.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RecoverChangePasswordRequest {
    String newPassword;
}
