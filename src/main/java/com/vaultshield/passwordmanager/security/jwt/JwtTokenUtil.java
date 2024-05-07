package com.vaultshield.passwordmanager.security.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_RECOVER}")
    private String recoverSecret;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getRecoverSingKey() {
        
        byte[] keyBytes = Decoders.BASE64.decode(recoverSecret);
        
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(30, ChronoUnit.MINUTES);

        System.out.println(getSigningKey());

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateTokenFromUsername(String username) {

        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(30, ChronoUnit.MINUTES);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        }

        return false;
    }

    public Instant extractTokenExpiration(String jwtToken) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken);

        Date expirationDate = claimsJws.getBody().getExpiration();
        return expirationDate.toInstant();
    }

    public String generateRecoverToken(String id) {
        try {
            Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
            Instant expiration = issuedAt.plus(30, ChronoUnit.MINUTES);

            return Jwts.builder()
                .setSubject(id)
                .claim("type", "recover")
                .setExpiration(Date.from(expiration))
                .signWith(getRecoverSingKey(), SignatureAlgorithm.HS256)
                .compact();
        } catch (Exception e) {
            System.out.println("error by error generating token: " + e.getMessage());
            throw new IllegalStateException("Internal server error");
        }
    }

    public String validateRecoverToken(String token){
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(getRecoverSingKey()).build().parseClaimsJws(token);
            Claims body = claims.getBody();

            if (body.get("type").equals("recover")){
                return body.getSubject();
            }else{
                return null;
            }
            
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return null;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return null;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            return null;
        } catch (Exception e ) {
            log.error("internal error: {}", e);
            return null;
        }
    }
}
