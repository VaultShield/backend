package com.vaultshield.passwordmanager.services;

import org.springframework.http.ResponseEntity;

import com.vaultshield.passwordmanager.models.request.RecoverChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;

public interface Recover {
    ResponseEntity<RecoverResponse> recover(RecoverRequest request);
    ResponseEntity<?> recoverchange(RecoverChangePasswordRequest request, String header);
}
