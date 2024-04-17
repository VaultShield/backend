package com.vaultshield.passwordmanager.web;

import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;
import com.vaultshield.passwordmanager.services.impl.CredentialsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CredentialController {

    @Autowired
    CredentialsServiceImpl service;
    @PostMapping("/insert-credentials")
    private void getAllCredentials(@RequestBody CreateNewCredentialRequest request){
        service.insertCredential(request);
    }

    @GetMapping("/update-credential/{credential-id}")
    private void updateCredentials(){}

    @PostMapping("/delete-credential/{credential-id}")
    private void deleteCredential(){}

    @PostMapping("/add-credential")
    private void createCredential(){}
}
