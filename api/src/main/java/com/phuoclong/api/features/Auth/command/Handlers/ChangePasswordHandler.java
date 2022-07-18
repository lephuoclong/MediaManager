package com.phuoclong.api.features.Auth.command.Handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.command.ChangePassword;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("ChangePasswordHandler")
public class ChangePasswordHandler implements Command.Handler<ChangePassword, ResponseMessageOf<String>> {

    private AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMessageOf<String> handle(ChangePassword command) {

        var accountChange = accountRepository.findById(command.getAccountId());

        if ( accountChange.isEmpty()) {
            return ResponseMessageOf.of(HttpStatus.NOT_FOUND);
        }

        if (command.getToken().isBlank()){
            return ResponseMessageOf.ofBadRequest("Incorrect secret key!", Map.of());
        }

        if(command.getNewPassword().equals(command.getConfirmPassword())){
            var newAccount = accountChange.get();
            newAccount.setPassword(passwordEncoder.encode(command.getNewPassword()));
            newAccount.setToken(null);

            accountRepository.saveAndFlush(newAccount);

            return ResponseMessageOf.of(HttpStatus.ACCEPTED);
        }

        return ResponseMessageOf.ofBadRequest("Confirm password failed", Map.of());
    }
}
