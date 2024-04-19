package com.vaultshield.passwordmanager.models.response;

import java.time.LocalDateTime;

import com.vaultshield.passwordmanager.documentation.ExampleValues;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @Schema(description = "User ID", example = ExampleValues.ID)
    private String id;

    @Schema(description = "Username", example = ExampleValues.USERNAME)
    private String username;

    @Schema(description = "User email", example = ExampleValues.EMAIL)
    private String email;

    @Schema(description = "Is user active?", example = ExampleValues.ACTIVE)
    private Boolean active;

    @Schema(description = "User update date", example = ExampleValues.UPDATE_DATE)
    private LocalDateTime updateDate;

    @Schema(description = "User delete date", example = ExampleValues.SOFT_DELETE_DATE)
    private LocalDateTime softDeleteDate;

}
