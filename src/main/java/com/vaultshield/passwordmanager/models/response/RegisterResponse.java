package com.vaultshield.passwordmanager.models.response;

import java.util.List;

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
public class RegisterResponse {

    @Schema(description = "User ID", example = ExampleValues.ID)
    private String id;

    @Schema(description = "User Seed phrase", example = ExampleValues.SEED)
    private List<String> seedPhrase;
}
