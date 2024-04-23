package com.vaultshield.passwordmanager.models.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecoverRequest {
    public String username;
    public List<String> seedphrase;
}
