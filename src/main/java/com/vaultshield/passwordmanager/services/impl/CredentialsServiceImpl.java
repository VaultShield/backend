package com.vaultshield.passwordmanager.services.impl;

import com.vaultshield.passwordmanager.mapper.DtoAndEntityMapper;
import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.ChangedCredentialsRequest;
import com.vaultshield.passwordmanager.models.request.CommonIdRequest;
import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;
import com.vaultshield.passwordmanager.models.request.FindCredentialsRequest;
import com.vaultshield.passwordmanager.repository.CredentialsRepository;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.services.CredentialsService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CredentialsServiceImpl implements CredentialsService {
    @Autowired
    CredentialsRepository credentialsRepository;

    @Autowired
    LoginAndRegistrationRepository loginAndRegistrationRepository;
    @Autowired
    DtoAndEntityMapper mapper;
    @Override
    public void insertCredential(CreateNewCredentialRequest request) {

        CredentialsEntity entity = new CredentialsEntity();
        //entity.setCredentialTypeId(request.getCredentialTypeId());
        entity.setCreateDate(LocalDateTime.now());

        UserEntity userEntity = loginAndRegistrationRepository.findById(request.getUserId()).orElse(null);
        if (userEntity == null) {
            throw new EntityNotFoundException("No se encontró el usuario con el ID: " + request.getUserId());
        }
        entity.setUser(userEntity);


        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setTitle(request.getTitle());
        passwordEntity.setAccount(request.getAccount());
        passwordEntity.setPassword(request.getPassword());
        passwordEntity.setNote(request.getNote());
        passwordEntity.setCredentials(entity);

        entity.setPassword(passwordEntity);

        credentialsRepository.save(entity);
    }

    @Override
    public void modifyCredential(ChangedCredentialsRequest request) {
       Optional<CredentialsEntity> entity  = credentialsRepository.findById(request.getCredentialId());
     //  password.setAccount(request.getAccount());
      // password.setTitle(request.getTitle());
      // password.setPassword(request.getPassword());

        if (entity.isPresent()){
            entity.get().getPassword().setPassword(request.getPassword());
            entity.get().setUpdateDate(LocalDateTime.now());
            entity.get().getPassword().setAccount(request.getAccount());
            credentialsRepository.save(entity.get());
        }
    }

    @Override
    @Transactional
    public void deleteCredential(CommonIdRequest request) {
       // Optional<CredentialsEntity> entity  = credentialsRepository.findById(request.getId());
        credentialsRepository.deleteById(request.getId());
    }

    @Override
    public List<Credentials> findAllCredentials(FindCredentialsRequest request) {
        List<CredentialsEntity> credentialsEntities = credentialsRepository.findCredentialsEntityByUserId(request.getUserId()).get();

        List<Credentials>  response;
        response = credentialsEntities.stream()
                .map(mapper::credentialsEntityToCredentialsDto)
                .collect(Collectors.toList());
        return response;
    }

    @Override
    public Credentials findOneCredential() {
        return null;
    }
}
