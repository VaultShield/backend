package com.vaultshield.passwordmanager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.mapper.DtoAndEntityMapper;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.CommonIdRequest;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;
import com.vaultshield.passwordmanager.repository.CredentialsRepository;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.services.CredentialsService;

import jakarta.transaction.Transactional;

@Service
public class CredentialsServiceImpl implements CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    LoginAndRegistrationRepository loginAndRegistrationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoAndEntityMapper mapper;

    @Autowired
    PasswordServiceImpl passwordService;

    @Override
    public CredentialsEntity insertCredential(CredentialRequest request) throws SaveException {

        CredentialsEntity entity = new CredentialsEntity();
        //entity.setCredentialTypeId(request.getCredentialTypeId());
        entity.setCreateDate(LocalDateTime.now());

        UserEntity userEntity = userRepository.findById(request.getUserId()).orElse(null);
        if (userEntity == null) {
            throw new SaveException("User not found with ID: " + request.getUserId());
        }
        entity.setUser(userEntity);


        PasswordEntity passwordEntity = passwordService.createPassword(request);
        passwordEntity.setCredentials(entity);
        entity.setPassword(passwordEntity);
        entity.setState("Active");
        entity.setFavorite(request.getFavorite());
        entity.setGroupId(request.getGroupId());

        return credentialsRepository.save(entity);
    }

    @Override
    public CredentialsEntity modifyCredential(CredentialRequest request, String id)
            throws SaveException, NotFoundException {
        Optional<CredentialsEntity> entityToUpdate = credentialsRepository.findById(id);
        if (!entityToUpdate.isPresent()) {
            throw new NotFoundException("No credentials found with ID: " + id);
        }
        CredentialsEntity credentialToUpdate = entityToUpdate.get();
        if (request.getPassword() != null) {
            String passwordId = credentialToUpdate.getPassword().getId();
            PasswordEntity passwordEntity = passwordService.updatePassword(request, passwordId);
            passwordEntity.setCredentials(credentialToUpdate);
            credentialToUpdate.setPassword(passwordEntity);
        }
        if (request.getFavorite() != null) {
            credentialToUpdate.setFavorite(request.getFavorite());
        }
        if (request.getGroupId() != null) {
            credentialToUpdate.setGroupId(request.getGroupId());
        }
     credentialToUpdate.setState("Active");
     credentialToUpdate.setUpdateDate(LocalDateTime.now());
     return credentialsRepository.save(credentialToUpdate);
    }

    @Override
    @Transactional
    public void deleteCredential(CommonIdRequest request) {
        // entityToUpdateOptional<CredentialsEntity> entity =
        // credentialsRepository.findById(request.getId());
        credentialsRepository.deleteById(request.getId());
    }

    @Override
    public List<CredentialsEntity> findAllCredentials(String userId) throws NotFoundException {
        List<CredentialsEntity> credentialsEntities = credentialsRepository.findCredentialsEntityByUserId(userId).get();
        if (credentialsEntities.isEmpty()) {
            throw new NotFoundException("No credentials found for user: " + userId);
        }
        return credentialsEntities;
    }

    @Override
    public CredentialsEntity findOneCredential(String id) throws NotFoundException {
        Optional<CredentialsEntity> entity = credentialsRepository.findById(id);
        if (!entity.isPresent()) {
            throw new NotFoundException("No credentials found with ID: " + id);
        }
        return entity.get();
    }
}
