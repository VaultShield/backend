package com.vaultshield.passwordmanager.services.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaultshield.passwordmanager.exceptions.ConflictException;
import com.vaultshield.passwordmanager.exceptions.NotFoundException;
import com.vaultshield.passwordmanager.exceptions.QueryError;
import com.vaultshield.passwordmanager.exceptions.SaveException;
import com.vaultshield.passwordmanager.exceptions.TokenRefreshException;
import com.vaultshield.passwordmanager.models.entities.UserEntity;
import com.vaultshield.passwordmanager.models.request.LoginRequest;
import com.vaultshield.passwordmanager.models.request.RegisterRequest;
import com.vaultshield.passwordmanager.models.request.UserRequest;
import com.vaultshield.passwordmanager.models.response.LoginResponse;
import com.vaultshield.passwordmanager.models.response.RegisterResponse;
import com.vaultshield.passwordmanager.repository.UserRepository;
import com.vaultshield.passwordmanager.security.jwt.JwtTokenUtil;
import com.vaultshield.passwordmanager.security.model.RefreshTokenEntity;
import com.vaultshield.passwordmanager.security.model.request.TokenRefreshRequest;
import com.vaultshield.passwordmanager.security.model.response.TokenRefreshResponse;
import com.vaultshield.passwordmanager.security.service.RefreshTokenService;
import com.vaultshield.passwordmanager.services.UserService;
import com.vaultshield.passwordmanager.utils.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    String ZONE_ID = "CET";

    @Override
    public ResponseEntity<RegisterResponse> registerUser(RegisterRequest signUpRequest) throws ConflictException {

        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new ConflictException(ErrorMessages.ERROR_409_USERNAME);

        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new ConflictException(ErrorMessages.ERROR_409_EMAIL);

        UserEntity newUser = new UserEntity();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setPassword(encoder.encode(signUpRequest.getPassword()));
        newUser.setActive(true);
        userRepository.save(newUser);
        return new ResponseEntity<>(new RegisterResponse(newUser.getId()), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) throws NotFoundException {
        if (!userRepository.existsByUsername(loginRequest.getUsername())) {
            throw new NotFoundException(ErrorMessages.ERROR_404_USERNAME);
        }

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);
        Instant expiration = jwtTokenUtil.extractTokenExpiration(jwt);
        ZoneId cetZone = ZoneId.of(ZONE_ID);
        ZonedDateTime expirationCET = ZonedDateTime.ofInstant(expiration, cetZone);

        UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).get();
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return new ResponseEntity<>(new LoginResponse(jwt, refreshToken.getToken(), expirationCET.toString()),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> refreshtoken(TokenRefreshRequest request) {

        String requestRefreshToken = request.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshTokenEntity::getUser)
                .map(user -> {
                    String token = jwtTokenUtil.generateTokenFromUsername(user.getUsername());
                    Instant expiration = jwtTokenUtil.extractTokenExpiration(token);
                    ZoneId cetZone = ZoneId.of(ZONE_ID);
                    ZonedDateTime expirationCET = ZonedDateTime.ofInstant(expiration, cetZone);

                    RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(user.getId());

                    return ResponseEntity
                            .ok(new TokenRefreshResponse(token, refreshToken.getToken(), expirationCET.toString()));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        ErrorMessages.TOKEN_NOT_EXIST));
    }

    @Override
    public UserEntity getUserById(String id) throws NotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);

        return user.get();
    }

    @Override
    public UserEntity changeUserData(UserRequest user, String userId) throws NotFoundException, SaveException {
        Optional<UserEntity> userToChange = userRepository.findById(userId);
        if (!userToChange.isPresent())
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);
        if (!userToChange.get().getUsername().equals(user.getUsername())
                && userRepository.existsByUsername(user.getUsername()))
            throw new SaveException(ErrorMessages.ERROR_409_USERNAME);
        if (!userToChange.get().getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail()))
            throw new SaveException(ErrorMessages.ERROR_409_EMAIL);
        UserEntity existingUser = userToChange.get();
        if (user.getEmail() != null)
            existingUser.setEmail(user.getEmail());

        if (user.getUsername() != null)
            existingUser.setUsername(user.getUsername());

        return userRepository.save(existingUser);
    }

    @Override
    public UserEntity removeUser(String id) throws NotFoundException {
        Optional<UserEntity> userToDelete = userRepository.findById(id);
        if (!userToDelete.isPresent())
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);
        userRepository.deleteById(id);
        return userToDelete.get();
    }

    @Override
    public UserEntity getUserByEmail(String email) throws NotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (!user.isPresent())
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND + " - " + ErrorMessages.ERROR_404_EMAIL);

        return user.get();
    }

    @Override
    public UserEntity getUserByUsername(String username) throws NotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if (!user.isPresent())
            throw new NotFoundException(
                    ErrorMessages.USER_NOT_FOUND + " - " + ErrorMessages.ERROR_404_USERNAME);

        return user.get();
    }

    @Override
    public UserEntity searchUser(String by, String value) throws NotFoundException, QueryError {
        UserEntity user = null;
        switch (by) {
            case "id":
                user = getUserById(value);
                break;
            case "email":
                user = getUserByEmail(value);
                break;
            case "username":
                user = getUserByUsername(value);
                break;
            default:
                throw new QueryError(ErrorMessages.INCORRECT_QUERY);
        }
        return user;
    }
}
