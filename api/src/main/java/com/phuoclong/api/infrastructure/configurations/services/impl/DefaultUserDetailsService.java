package com.phuoclong.api.infrastructure.configurations.services.impl;

import com.phuoclong.api.infrastructure.configurations.services.UserDetailsModelService;
import com.phuoclong.api.infrastructure.configurations.services.UserDetailsService;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.repositories.RoleAccountShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleAccountShareRepository roleAccountShareRepository;

    @Override
    @Transactional
    public UserDetails loadUserByAccount(String account) throws UsernameNotFoundException {
        var accountEntity = accountRepository.findByAccount(account)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found with username: " + account));

        var roles = roleAccountShareRepository.findAllByAccountId(accountEntity.getId());
        return UserDetailsModelService.build(accountEntity, roles);
    }
}
