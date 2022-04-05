package com.phuoclong.api.features.Auth.command.Handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("RegisterHandler")
public class RegisterAccountHandler implements Command.Handler<RegisterAccount, ResponseMessageOf<String>> {
    @Override
    public ResponseMessageOf<String> handle(RegisterAccount command) {

        return null;
    }
}
