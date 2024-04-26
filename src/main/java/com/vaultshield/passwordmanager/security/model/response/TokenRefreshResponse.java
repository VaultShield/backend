package com.vaultshield.passwordmanager.models.response;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenRefreshResponse {

    @Schema(description = "User Token", example = ExampleValues.TOKEN)
    private String token;

    @Schema(description = "Refresh Token", example = ExampleValues.REFRESH_TOKEN)
    private String refreshToken;

    @Schema(description = "Token Type", example = ExampleValues.TOKEN_TYPE)
    private String tokenType = "Bearer";

    @Schema(description = "Token Expiration", example = ExampleValues.EXPIRATION)
    private String expiration;

    public TokenRefreshResponse(String token, String refreshToken, String expiration) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }
}
