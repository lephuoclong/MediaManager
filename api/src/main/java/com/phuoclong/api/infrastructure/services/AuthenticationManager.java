package com.phuoclong.api.infrastructure.services;

import org.springframework.security.core.Authentication;

public interface AuthenticationManager {
    Authentication getAuthentication();
}
