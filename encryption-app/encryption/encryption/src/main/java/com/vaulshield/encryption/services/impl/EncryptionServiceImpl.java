package com.vaulshield.encryption.services.impl;

import com.vaulshield.encryption.helper.AES256;
import com.vaulshield.encryption.models.request.EncrypPasswordRequest;
import com.vaulshield.encryption.models.response.EncrypPasswordResponse;
import com.vaulshield.encryption.services.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@Slf4j
public class EncryptionServiceImpl implements EncryptionService {

    private final AES256 aes256;
    @Override
    public EncrypPasswordResponse encryptionValue(EncrypPasswordRequest request)  {
        EncrypPasswordResponse response = new EncrypPasswordResponse();
        if (request.getValue() != null) {
            log.info("encryp with aes");
            if(request.getSymmetric() != null && request.getSymmetric()){
                response.setValue(aes256.encrypt(request.getValue()));
            }
            log.info("encryp with sha");
            if (request.getSymmetric() != null && !request.getSymmetric()){
                response.setValue(DigestUtils.sha256Hex(request.getValue()));
            }
            log.info("desencryp with aes");
            if(request.getReverse() != null && request.getReverse()) {
                response.setValue(aes256.decrypt(request.getValue()));
            }
            return  response;
        }
         return  null;
    }
}
