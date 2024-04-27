package com.vaultshield.passwordmanager.models.request;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RecoverRequest {
    public String username;
    public List<String> seedphrase;
}
