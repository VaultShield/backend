package com.vaulshield.encryption.controller;

import com.vaulshield.encryption.models.request.EncrypPasswordRequest;
import com.vaulshield.encryption.models.response.EncrypPasswordResponse;
import com.vaulshield.encryption.services.impl.EncryptionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encryption")
@RequiredArgsConstructor
@Slf4j
public class EncryptionController {
    private final EncryptionServiceImpl service;
    @PostMapping()
    EncrypPasswordResponse encryptionValue(@RequestBody  EncrypPasswordRequest request) {
        return service.encryptionValue(request);
    }
}
