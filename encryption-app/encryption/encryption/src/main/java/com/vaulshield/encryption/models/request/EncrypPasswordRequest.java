package com.vaulshield.encryption.models.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncrypPasswordRequest {

    private String value;
    private Boolean symmetric;
    private Boolean reverse;
}
