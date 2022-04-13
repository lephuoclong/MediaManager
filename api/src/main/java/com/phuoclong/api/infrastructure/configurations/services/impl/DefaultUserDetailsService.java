package com.phuoclong.api.infrastructure.configurations.services.impl;

import com.phuoclong.api.infrastructure.configurations.services.UserDetailsModelService;
import com.phuoclong.api.infrastructure.configurations.services.UserDetailsService;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserDetailsService implements UserDetailsService {

    AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var accountEntity = accountRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsModelService.build(accountEntity);
    }
}
