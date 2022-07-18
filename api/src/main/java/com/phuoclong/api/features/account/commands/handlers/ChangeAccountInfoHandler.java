package com.phuoclong.api.features.account.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.account.commands.ChangeAccountInfo;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("ChangeAccountInfoHandler")
public class ChangeAccountInfoHandler implements Command.Handler<ChangeAccountInfo, ResponseMessageOf<String>> {

    private AccountRepository accountRepository;
    @Override
    public ResponseMessageOf<String> handle(ChangeAccountInfo command) {

        var accountChange = accountRepository.findById(command.getAccountId());

        if(accountChange.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Your cannot change this account information!", Map.of());
        }

        var accountEntity = accountChange.get();
        if (accountEntity.getFirstName().equals( command.getFirstName()) && accountEntity.getLastName().equals(command.getLastName())){
            return ResponseMessageOf.ofBadRequest("The new change is the same as the old information", Map.of());
        }

        accountEntity.setFirstName(command.getFirstName());
        accountEntity.setLastName(command.getLastName());
        accountRepository.saveAndFlush(accountEntity);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED);
    }
}
