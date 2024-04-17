package com.vaultshield.passwordmanager.services.impl;

import com.vaultshield.passwordmanager.config.PasswordManagerProperties;
import com.vaultshield.passwordmanager.mapper.DtoAndEntityMapper;
import com.vaultshield.passwordmanager.models.dto.Credentials;
import com.vaultshield.passwordmanager.models.entities.CredentialsEntity;
import com.vaultshield.passwordmanager.models.entities.PasswordEntity;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.CreateNewCredentialRequest;
import com.vaultshield.passwordmanager.repository.CredentialsRepository;
import com.vaultshield.passwordmanager.repository.LoginAndRegistrationRepository;
import com.vaultshield.passwordmanager.services.CredentialsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
        // Crear una nueva instancia de CredentialsEntity y asignar los valores del DTO
        CredentialsEntity entity = new CredentialsEntity();
        //entity.setCredentialTypeId(request.getCredentialTypeId());
        entity.setCreateDate(LocalDateTime.now());
        // Asignar el usuario a la entidad de credenciales
        UserEntity userEntity = loginAndRegistrationRepository.findById(request.getUserId()).orElse(null);
        if (userEntity == null) {
            throw new EntityNotFoundException("No se encontró el usuario con el ID: " + request.getUserId());
        }
        entity.setUser(userEntity);

        // Crear una nueva instancia de PasswordEntity y asignar los valores del DTO
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setTitle(request.getTitle());
        passwordEntity.setAccount(request.getAccount());
        passwordEntity.setPassword(request.getPassword());
        passwordEntity.setNote(request.getNote());
        passwordEntity.setCredentials(entity); // Asignar la entidad de credenciales a la contraseña

        // Asignar la contraseña a la entidad de credenciales
        entity.setPassword(passwordEntity);

        // Guardar la entidad de credenciales en la base de datos
        credentialsRepository.save(entity);
    }
}
