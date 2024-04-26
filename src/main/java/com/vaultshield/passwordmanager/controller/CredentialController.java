package com.vaultshield.passwordmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.documentation.ErrorExamples.NotFoundCredentialExample;
import com.vaultshield.passwordmanager.documentation.ErrorExamples.NotFoundErrorExample;
import com.vaultshield.passwordmanager.mapper.CredentialMapper;
import com.vaultshield.passwordmanager.models.request.CredentialRequest;
import com.vaultshield.passwordmanager.models.response.CredentialResponse;
import com.vaultshield.passwordmanager.services.impl.CredentialsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Credentials", description = "Create/Update/Delete user credentials")
@RestController
@RequestMapping("/api/credential")
public class CredentialController {

    @Autowired
    CredentialsServiceImpl service;

    @Operation(summary = "Add a new credential")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CredentialResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
    })
    @PostMapping("/insert")
    private CredentialResponse getAllCredentials(@RequestBody CredentialRequest request) {
        return CredentialMapper.toCredentialResponse(service.insertCredential(request));
    }

    @Operation(summary = "Update an existing credential")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Credential updated successfully", content = @Content(schema = @Schema(implementation = CredentialResponse.class))),
            @ApiResponse(responseCode = "404", description = "Credential not found", content = @Content(schema = @Schema(implementation = NotFoundCredentialExample.class))),
    })
    @PatchMapping("/{id}")
    private CredentialResponse updateCredentials(@RequestBody CredentialRequest request,
            @PathVariable String id) {
        return CredentialMapper.toCredentialResponse(service.modifyCredential(request, id));
    }

    @Operation(summary = "Delete an existing credential")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Credential not found", content = @Content(schema = @Schema(implementation = NotFoundCredentialExample.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCredential(@PathVariable String id) {
        service.deleteCredential(id);
    }

    @Operation(summary = "Find all user's credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CredentialResponse.class)))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = NotFoundErrorExample.class)))
    })
    @GetMapping("/find-all/{userId}")
    private List<CredentialResponse> createCredential(@PathVariable String userId) {
        return CredentialMapper.toCredentialResponseList(service.findAllCredentials(userId));
    }

    @Operation(summary = "See a credential by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CredentialResponse.class))),
            @ApiResponse(responseCode = "404", description = "Credential not found", content = @Content(schema = @Schema(implementation = NotFoundCredentialExample.class)))
    })
    @GetMapping("/{id}")
    private CredentialResponse findCredential(@PathVariable String id) {
        return CredentialMapper.toCredentialResponse(service.findOneCredential(id));
    }

}
