package com.phuoclong.api.infrastructure.configurations.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadUserByAccount(String account) throws UsernameNotFoundException;
}
