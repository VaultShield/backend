package com.vaultshield.passwordmanager.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.models.request.RecoverNewPasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.models.response.RecoverNewPasswordResponse;
import com.vaultshield.passwordmanager.models.response.RecoverResponse;
import com.vaultshield.passwordmanager.services.impl.RecoverImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecoverController {

    final private RecoverImpl service;

    @PostMapping("/recover")
    public ResponseEntity<?> recover(@RequestBody RecoverRequest request) {
        RecoverResponse response = service.newRecover(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/recover")
    public ResponseEntity<?> putrecover(@RequestBody RecoverNewPasswordRequest request, @RequestHeader("Recover") String header) {
        RecoverNewPasswordResponse response = service.newPasswordRecover(request, header);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
