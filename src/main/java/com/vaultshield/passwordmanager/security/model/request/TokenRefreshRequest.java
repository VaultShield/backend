package com.vaultshield.passwordmanager.security.model.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;
import com.vaultshield.passwordmanager.utils.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = ExampleValues.REFRESH_TOKEN)
    @NotNull(message = ValidationMessages.REFRESH_TOKEN_REQUIRED)
    private String refreshToken;
}
