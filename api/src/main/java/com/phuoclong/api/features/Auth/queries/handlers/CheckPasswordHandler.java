package com.phuoclong.api.features.Auth.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.queries.CheckPassword;
import com.phuoclong.api.features.Auth.responses.LoginResponse;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Component("CheckPasswordHandler")
public class CheckPasswordHandler implements Command.Handler<CheckPassword, ResponseMessageOf<LoginResponse>> {
    private AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseMessageOf<LoginResponse> handle(CheckPassword query) {

        var accountCheck = accountRepository.findById(query.getAccountId());

        if (accountCheck.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Your account is disable!", Map.of());
        }
        var accountChange = accountCheck.get();

        var checkPassword = passwordEncoder.matches(query.getPassword(),accountChange.getPassword());

        if ( checkPassword){

            var token = UUID.randomUUID().toString();
            accountChange.setToken(token);
            accountRepository.saveAndFlush(accountChange);

            return ResponseMessageOf.of(HttpStatus.OK, LoginResponse.of(token));
        }

        return ResponseMessageOf.ofBadRequest("Incorrect password", Map.of());
    }
}
