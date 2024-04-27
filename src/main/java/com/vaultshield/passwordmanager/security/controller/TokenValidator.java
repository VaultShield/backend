package com.vaultshield.passwordmanager.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaultshield.passwordmanager.security.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/intern")
public class TokenValidator {

    @Autowired
    private JwtTokenUtil jwt;

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if (token.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        if (jwt.validateJwtToken(token)){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
}
