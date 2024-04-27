package com.vaulshield.encryption.services;

import com.vaulshield.encryption.models.request.EncrypPasswordRequest;
import com.vaulshield.encryption.models.response.EncrypPasswordResponse;

import java.security.NoSuchAlgorithmException;

public interface EncryptionService {
    EncrypPasswordResponse encryptionValue(EncrypPasswordRequest request);
}
