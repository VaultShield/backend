package com.vaultshield.passwordmanager.models.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;

    private String username;

    private String email;

    private Boolean active;

    private LocalDateTime updateDate;

    private LocalDateTime softDeleteDate;

}
