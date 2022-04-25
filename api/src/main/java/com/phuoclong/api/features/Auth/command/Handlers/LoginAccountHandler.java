package com.phuoclong.api.features.Auth.command.Handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.command.LoginAccount;
import com.phuoclong.api.features.Auth.responses.LoginResponse;
import com.phuoclong.api.infrastructure.configurations.JwtUtils;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("LoginAccountHandler")
@AllArgsConstructor
public class LoginAccountHandler implements Command.Handler<LoginAccount, ResponseMessageOf<LoginResponse>> {

    private final AuthenticationManager authenticationManager;

    private final AccountRepository accountRepository;

    private final JwtUtils jwtUtils;

    @Override
    public ResponseMessageOf<LoginResponse> handle(LoginAccount command) {



        var accountLogin = accountRepository.findByUsername(command.getUsername());

        if (accountLogin.isPresent()){
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(command.getUsername(), command.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            var jwt = jwtUtils.generateJwtToken(authentication);

            if (accountLogin.get().getStatus() == 0){
                var activateAccount = accountLogin.get();
                activateAccount.setStatus(1);
                accountRepository.saveAndFlush(activateAccount);
            }

            return ResponseMessageOf.of(HttpStatus.OK,"Login successfully",
                    Map.of(LoginAccount.Fields.username, "Login successfully"), LoginResponse.of(jwt));
        }

        return ResponseMessageOf.ofBadRequest("Username or Password is incorrect", Map.of());
    }
}
