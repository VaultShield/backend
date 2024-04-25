package com.vaultshield.passwordmanager.models.request;

import com.vaultshield.passwordmanager.documentation.ExampleValues;
import com.vaultshield.passwordmanager.utils.ValitationMessages;

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

    @NotBlank(message = ValitationMessages.USERNAME_REQUIRED)
    @Schema(example = ExampleValues.USERNAME)
    public String username;

    @NotBlank(message = ValitationMessages.PASSWORD_REQUIRED)
    @Schema(example = ExampleValues.PASSWORD)
    public String password;

    @NotBlank(message = ValitationMessages.EMAIL_REQUIRED)
    @Email(message = ValitationMessages.WRONG_EMAIL)
    @Schema(example = ExampleValues.EMAIL)
    public String email;
}
