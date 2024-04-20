package com.vaultshield.passwordmanager.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;
import com.vaultshield.passwordmanager.repository.PasswordRepository;
import com.vaultshield.passwordmanager.services.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @Autowired
    private PasswordRepository passwordRepository;

    @Override
    public PasswordEntity createPassword(CredentialRequest request) throws SaveException {
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setTitle(request.getTitle());
        passwordEntity.setAccount(request.getAccount());
        passwordEntity.setPassword(bcrypt.encode(request.getPassword()));
        passwordEntity.setNote(request.getNote());

        return passwordRepository.save(passwordEntity);
    }

}
