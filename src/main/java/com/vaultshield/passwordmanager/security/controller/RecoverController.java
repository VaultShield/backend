package com.vaultshield.passwordmanager.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.models.request.RecoverChangePasswordRequest;
import com.vaultshield.passwordmanager.models.request.RecoverRequest;
import com.vaultshield.passwordmanager.services.impl.RecoverImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecoverController {

    final private RecoverImpl service;

    @PostMapping("/recover")
    public ResponseEntity<?> recover(@RequestBody RecoverRequest request) {
        return service.recover(request);
    }

    @PutMapping("/recover")
    public ResponseEntity<?> putrecover(@RequestBody RecoverChangePasswordRequest request, @RequestHeader("Recover") String header) {
        return service.recoverchange(request, header);
    }

}
