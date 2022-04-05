package com.phuoclong.api.infrastructure.common;

import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtExpirationMs}")
    private Long jwtExpirationMs;

    private AccountRepository accountRepository;

    public String generateJwtToken(UUID accountId, String srkey){

        var accountEntity = accountRepository.findById(accountId);

        if (accountEntity.isPresent()){
            Map<String, Object> claims = new HashMap<>();
            claims.put("sub", accountEntity.get().getId().toString());
            claims.put("email", accountEntity.get().getEmail());
            claims.put("given_name", accountEntity.get().getFirstName());
            claims.put("family_name", accountEntity.get().getLastName());

            return Jwts.builder()
                    .setSubject(accountEntity.get().getId().toString())
                    .setIssuer(accountEntity.get().getEmail())
                    .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS256, srkey)
                    .setClaims(claims)
                    .compact();
        }
        return null;
    }
}
