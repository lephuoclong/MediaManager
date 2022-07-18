package com.phuoclong.api.infrastructure.configurations;

import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final AccountRepository accountRepository;

    @Value("${bezkoder.app.jwtSecretKey}")
    private String jwtSecret;
    @Value("${bezkoder.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        var userPrincipal =(User) authentication.getPrincipal();
        var accountEntity = accountRepository.findByUsername(userPrincipal.getUsername());

        if(accountEntity.isEmpty()){
            return null;
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", accountEntity.get().getId().toString());
        claims.put("sub", accountEntity.get().getAccount());
        claims.put("email", accountEntity.get().getEmail());
        claims.put("given_name", accountEntity.get().getFirstName());
        claims.put("family_name", accountEntity.get().getLastName());
        return Jwts.builder()
                .setIssuer(String.valueOf(new Date().getTime()))
                .setClaims(claims)
                .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
