package com.phuoclong.api.infrastructure.services;

import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var accountEntity = accountRepository.findByAccount(username);

        if (accountEntity.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(accountEntity.get().getUsername().toString(),
                accountEntity.get().getPassword(), new ArrayList<>());
    }

    public AccountEntity save(RegisterAccount registerAccount){
        var accountEntity = new AccountEntity();
        accountEntity.setFirstName(registerAccount.getFirstName());
        accountEntity.setAccount(registerAccount.getUsername());
        accountEntity.setLastName(registerAccount.getLastName());
        accountEntity.setEmail(registerAccount.getEmail());
        accountEntity.setPassword(registerAccount.getPassword());
        accountEntity.setStatus(0);
        return accountRepository.save(accountEntity);
    }
}
