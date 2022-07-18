package com.phuoclong.api.features.Auth.command.Handlers;

import an.awesome.pipelinr.Command;
import com.google.common.base.Strings;
import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.features.Auth.responses.RegisterResponse;
import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component("RegisterAccountHandler")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterAccountHandler implements Command.Handler<RegisterAccount, ResponseMessageOf<RegisterResponse>> {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(RegisterAccountHandler.class);

    @Override
    public ResponseMessageOf<RegisterResponse> handle(RegisterAccount command) {
        if (Strings.isNullOrEmpty(command.getUsername())
                || Strings.isNullOrEmpty(command.getFirstName())
                || Strings.isNullOrEmpty(command.getLastName())
                || Strings.isNullOrEmpty(command.getEmail())
                || Strings.isNullOrEmpty(command.getPassword())
                || Strings.isNullOrEmpty(command.getConfirmPassword())) {
            return ResponseMessageOf.ofBadRequest("Some information is not enough!", Map.of());
        }

        if (!command.getPassword().equals(command.getConfirmPassword())) {
            return ResponseMessageOf.ofBadRequest("No matching password!", Map.of());
        }

        if (Boolean.TRUE.equals(accountRepository.existsByEmail(command.getEmail()))) {
            return ResponseMessageOf.ofBadRequest("Email is already in use!", Map.of());
        }
        if (Boolean.TRUE.equals(accountRepository.existsByAccount(command.getUsername()))) {
            return ResponseMessageOf.ofBadRequest("Username is already taken!", Map.of());
        }

        try {
            var newAccount = new AccountEntity();

            newAccount.setAccount(command.getUsername());
            newAccount.setEmail(command.getEmail());
            newAccount.setFirstName(command.getFirstName());
            newAccount.setLastName(command.getLastName());
            newAccount.setPassword(passwordEncoder.encode(command.getPassword()));

            var newCreateAccount = accountRepository.save(newAccount);

            newCreateAccount.setUsername(newCreateAccount.getId().toString());

            accountRepository.saveAndFlush(newCreateAccount);

            var message = "Tài khoản " + command.getUsername() +
                    " được đăng ký thành công";

            return ResponseMessageOf.of(HttpStatus.ACCEPTED,
                    message,
                    Map.of(RegisterAccount.Fields.username, message),
                    RegisterResponse.of(command.getUsername(), message));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseMessageOf.ofBadRequest("Internal error", Map.of());
        }

    }
}
