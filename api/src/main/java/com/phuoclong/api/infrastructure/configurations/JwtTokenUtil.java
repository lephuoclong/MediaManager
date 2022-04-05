package com.phuoclong.api.infrastructure.configurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${bezkoder.app.jwtExpirationMs}")
    private Long expirationMs;

    @Value("${bezkoder.app.jwtSecretKey}")
    private String secretKey;

    public UUID getAccountIdFromToken(String token) { return UUID.fromString(getClaimFromToken(token, Claims::getSubject)); }

    public OffsetDateTime getExpirationDateFromToken(String token) {
        return  getClaimFromToken(token, Claims::getExpiration).toInstant()
                .atOffset(ZoneOffset.UTC);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final OffsetDateTime expiration = getExpirationDateFromToken(token);
        return expiration.isBefore(new Date().toInstant().atOffset(ZoneOffset.UTC));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final UUID accountId = getAccountIdFromToken(token);
        return (accountId.toString().equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
