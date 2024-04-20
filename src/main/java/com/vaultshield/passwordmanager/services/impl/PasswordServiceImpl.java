package com.vaultshield.passwordmanager.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.NotFoundException;
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

    @Override
    public PasswordEntity updatePassword(CredentialRequest request, String id) throws SaveException, NotFoundException {
        Optional<PasswordEntity> passwordToUpdate = passwordRepository.findById(id);
        if (!passwordToUpdate.isPresent()) {
            throw new NotFoundException("No password found with ID: " + id);
        }
        PasswordEntity passwordEntity = passwordToUpdate.get();
        if (request.getTitle() != null) {
            passwordEntity.setTitle(request.getTitle());
        }
        if (request.getAccount() != null) {
            passwordEntity.setAccount(request.getAccount());
        }
        if (request.getPassword() != null) {
            passwordEntity.setPassword(bcrypt.encode(request.getPassword()));
        }
        if (request.getNote() != null) {
            passwordEntity.setNote(request.getNote());
        }

        return passwordRepository.save(passwordEntity);
    }

    @Override
    public PasswordEntity deletePassword(String id) throws NotFoundException {
        Optional<PasswordEntity> passwordToDelete = passwordRepository.findById(id);
        if (!passwordToDelete.isPresent()) {
            throw new NotFoundException("No password found with ID: " + id);
        }
        passwordRepository.deleteById(id);
        return passwordToDelete.get();
    }

}
