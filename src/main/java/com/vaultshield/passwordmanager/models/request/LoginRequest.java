package com.vaultshield.passwordmanager.models.request;


import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequest {

    @Schema(example = ExampleValues.USERNAME)
    public String username;

    @Schema(example = ExampleValues.PASSWORD)
    public String password;

}

