package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;
import com.vaultshield.passwordmanager.utils.ValidationMessages;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = ValidationMessages.USERNAME_REQUIRED)
    @Schema(example = ExampleValues.USERNAME)
    public String username;

    @NotBlank(message = ValidationMessages.PASSWORD_REQUIRED)
    @Schema(example = ExampleValues.PASSWORD)
    public String password;

    @NotBlank(message = ValidationMessages.EMAIL_REQUIRED)
    @Email(message = ValidationMessages.WRONG_EMAIL)
    @Schema(example = ExampleValues.EMAIL)
    public String email;
}
