package com.vaultshield.passwordmanager.web;

import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.request.ChangedCredentialsRequest;
import com.vaultshield.passwordmanager.models.request.CommonIdRequest;
import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;
import com.vaultshield.passwordmanager.models.request.FindCredentialsRequest;
import com.vaultshield.passwordmanager.services.impl.CredentialsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credential")
public class CredentialController {

    @Autowired
    CredentialsServiceImpl service;
    @PostMapping("/insert-credentials")
    private void getAllCredentials(@RequestBody CreateNewCredentialRequest request){
        service.insertCredential(request);
    }

    @PatchMapping("/update-credential")
    private void updateCredentials(@RequestBody  ChangedCredentialsRequest request){
        service.modifyCredential(request);
    }

    @DeleteMapping("/delete-credential")
    private void deleteCredential(@RequestBody  CommonIdRequest request){
        service.deleteCredential(request);
    }

    @PostMapping("/find-all-credential")
    private List<Credentials> createCredential(@RequestBody FindCredentialsRequest request){
       return service.findAllCredentials(request);
    }

    @PostMapping("/find-credential")
    private Credentials findCredential(@RequestBody  CommonIdRequest request){
      return service.findOneCredential(request);
    }


}
