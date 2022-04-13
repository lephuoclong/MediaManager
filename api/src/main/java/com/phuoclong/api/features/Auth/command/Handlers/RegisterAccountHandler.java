package com.phuoclong.api.features.Auth.command.Handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.features.Auth.responses.RegisterResponse;
import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("RegisterAccountHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterAccountHandler implements Command.Handler<RegisterAccount, ResponseMessageOf<RegisterResponse>> {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMessageOf<RegisterResponse> handle(RegisterAccount command) {

        if(Boolean.TRUE.equals(accountRepository.existsByEmail(command.getEmail()))){
            return ResponseMessageOf.ofBadRequest("Email is already in use!", Map.of());
        }
        if (Boolean.TRUE.equals(accountRepository.existsByUsername(command.getUsername()))){
            return ResponseMessageOf.ofBadRequest("Username is already taken!", Map.of());
        }

        var newAccount = new AccountEntity();

        newAccount.setUsername(command.getUsername());
        newAccount.setEmail(command.getEmail());
        newAccount.setFirstName(command.getFirstName());
        newAccount.setLastName(command.getLastName());
        newAccount.setPassword(passwordEncoder.encode(command.getPassword()));

        accountRepository.save(newAccount);

        var message = command.getUsername() +
                " được đăng ký thành công";

        return ResponseMessageOf.of(HttpStatus.ACCEPTED,
                message,
                Map.of(RegisterAccount.Fields.username, message),
                RegisterResponse.of(command.getUsername(), message));
    }
}
