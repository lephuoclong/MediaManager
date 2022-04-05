package com.phuoclong.api.infrastructure.services;

import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var accountEntity = accountRepository.findByEmail(email);

        if (accountEntity.isEmpty()){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(accountEntity.get().getEmail(),
                accountEntity.get().getPassword(), new ArrayList<>());
    }

    public AccountEntity save(RegisterAccount registerAccount){
        var accountEntity = new AccountEntity();
        accountEntity.setFirstName(registerAccount.getFistName());
        accountEntity.setLastName(registerAccount.getLastName());
        accountEntity.setEmail(registerAccount.getEmail());
        accountEntity.setPassword(registerAccount.getPassword());
        accountEntity.setStatus(0);
        return accountRepository.save(accountEntity);
    }
}