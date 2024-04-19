package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

    @Schema(example = ExampleValues.USERNAME)
    private String username;

    @Schema(example = ExampleValues.EMAIL)
    private String email;
}
