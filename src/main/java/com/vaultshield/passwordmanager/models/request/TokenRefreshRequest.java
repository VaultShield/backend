package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.utils.ValidationMessages;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenRefreshRequest {

    @NotNull(message = ValidationMessages.REFRESH_TOKEN_REQUIRED)
    private String refreshToken;
}
